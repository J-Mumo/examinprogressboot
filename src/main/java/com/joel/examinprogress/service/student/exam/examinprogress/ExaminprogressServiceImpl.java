/*
    =======================================================================================
    This code is part of Exam In Progress.

    Exam In Progress is an online exam software owned by Joel Mumo.

    The Exam In Progress software has a proprietary license. Please look at or request
    exam_in_progress_license.txt for further details.

    Copyright (C) 2020 JMumo

    Email:  jaymumo6@gmail.com

    ========================================================================================
    Author : Joel Mumo
    ========================================================================================
*/
package com.joel.examinprogress.service.student.exam.examinprogress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerType;
import com.joel.examinprogress.domain.exam.ExamTimerTypeEnum;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.domain.student.QuestionStatus;
import com.joel.examinprogress.domain.student.SectionStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.helper.time.TimeHelper;
import com.joel.examinprogress.repository.exam.ExamTimerTypeRepository;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.repository.student.ExamStatusRepository;
import com.joel.examinprogress.repository.student.QuestionStatusRepository;
import com.joel.examinprogress.repository.student.SectionStatusRepository;

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
@Service
public class ExaminprogressServiceImpl implements ExaminprogressService {

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private ExamTimerTypeRepository examTimerTypeRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private SectionStatusRepository sectionStatusRepository;

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @Autowired
    private AnswerTransferComparator answerTransferComparator;

    @Autowired
    private TimeHelper timeHelper;

    private AnswerTransfer createAnswerTransfer( Answer answer ) {

        AnswerTransfer transfer = new AnswerTransfer( answer.getId(), answer.getAnswerText() );

        return transfer;
    }


    private AnswerTransfer[] createAnswerTransfers( Question question ) {

        Set<Answer> answers = answerRepository.findByQuestion( question );
        SortedSet<AnswerTransfer> answerTransfers =
                new TreeSet<>( answerTransferComparator );

        for ( Answer answer : answers ) {

            answerTransfers.add( createAnswerTransfer( answer ) );
        }

        return answerTransfers.toArray( new AnswerTransfer[answerTransfers.size()] );
    }


    private Question getNextComprehensionSubQuestion( Question question, Student student ) {

        Set<Question> allQuestions = question.getQuestions();
        Set<Question> notFetchedQuestions = new HashSet<>();

        for ( Question subQuestion : allQuestions ) {

            QuestionStatus questionStatus =
                    questionStatusRepository
                    .findByQuestionAndStudent( subQuestion, student );

            if ( questionStatus == null || !questionStatus.getFetched() )
                notFetchedQuestions.add( subQuestion );

            if ( notFetchedQuestions.size() > 0 )
                return notFetchedQuestions.iterator().next();

        }
        return null;
    }


    private ExamQuestionTransfer createComprehensionSubQuestionTransfer( Question question,
            Student student ) {

        Long questionTime = question.getDuration() != null ? question.getDuration().toSeconds()
                : null;

        QuestionStatus questionStatus =
                questionStatusRepository
                .findByQuestionAndStudent( question, student );

        if ( questionStatus == null ) {
            questionStatus = new QuestionStatus();
            questionStatus.setQuestion( question );
            questionStatus.setStudent( student );
        }

        questionStatus.setFetched( Boolean.TRUE );
        questionStatus.setComplete( Boolean.FALSE );
        questionStatusRepository.save( questionStatus );

        AnswerTransfer[] answerTransfers = createAnswerTransfers( question );
        ExamQuestionTransfer questionTransfer = null;

        ExamQuestionTransfer examQuestionTransfer = new ExamQuestionTransfer( question.getId(),
                Boolean.FALSE, question.getQuestionText(), questionTime, questionTransfer,
                question.getAnswerType().getName(), answerTransfers );

        return examQuestionTransfer;
    }


    private ExamQuestionTransfer createQuestionTransfer( Question question, Student student ) {

        Long questionTime = question.getDuration() != null ? question.getDuration().toSeconds()
                : null;

        QuestionStatus questionStatus =
                questionStatusRepository
                .findByQuestionAndStudent( question, student );

        if ( questionStatus == null ) {
            questionStatus = new QuestionStatus();
            questionStatus.setQuestion( question );
            questionStatus.setStudent( student );
        }

        AnswerTransfer[] answerTransfers = createAnswerTransfers( question );
        boolean comprehensionQuestion = question.getQuestions().size() > 0 ? true : false;
        ExamQuestionTransfer questionTransfer = null;

        if ( comprehensionQuestion ) {

            questionStatus.setFetched( Boolean.FALSE );
            Question subQuestion = getNextComprehensionSubQuestion( question, student );

            if ( subQuestion == null ) {
                questionStatus.setFetched( Boolean.TRUE );
                questionStatus.setComplete( Boolean.TRUE );
                questionStatusRepository.save( questionStatus );
            }
            else
                questionTransfer = createComprehensionSubQuestionTransfer( subQuestion, student );
        }
        else {
            questionStatus.setFetched( Boolean.TRUE );
        }

        questionStatus.setComplete( Boolean.FALSE );
        questionStatusRepository.save( questionStatus );

        ExamQuestionTransfer examQuestionTransfer = new ExamQuestionTransfer( question.getId(),
                comprehensionQuestion, question.getQuestionText(), questionTime, questionTransfer,
                question.getAnswerType().getName(), answerTransfers );

        return examQuestionTransfer;
    }


    private Question getNextQuestion( Section section, Student student ) {

        Set<Question> allQuestions = section.getQuestions();
        Set<Question> notFetchedQuestions = new HashSet<>();

        for ( Question question : allQuestions ) {
            if ( question.getQuestion() == null ) {

                QuestionStatus questionStatus =
                        questionStatusRepository
                        .findByQuestionAndStudent( question, student );

                if ( questionStatus == null || !questionStatus.getFetched() )
                    notFetchedQuestions.add( question );

                if ( notFetchedQuestions.size() > 0 )
                    return notFetchedQuestions.iterator().next();

            }
        }
        return null;
    }


    private ExamSectionTransfer createSectionTransfer( Section section, Student student,
            Invite invite ) {

        ExamQuestionTransfer questionTransfer = null;
        boolean sectionIsComplete = false;
        Long sectionTime = null;
        Integer maxTimeSeconds = ( int )section.getDuration().toSeconds();

        SectionStatus sectionStatus =
                sectionStatusRepository
                .findBySectionAndStudent( section, student );

        if ( sectionStatus == null ) {
            sectionStatus = new SectionStatus();
            sectionStatus.setSection( section );
            sectionStatus.setStudent( student );
            sectionStatus.setTimeSpent( 0l );
            sectionStatus.setComplete( Boolean.FALSE );
            sectionStatusRepository.save( sectionStatus );
        }

        if ( invite.getPausable() ) {

            Long sectionTimeSpent = sectionStatus.getTimeSpent();
            sectionTime = maxTimeSeconds - sectionTimeSpent;
        }
        else {

            Calendar startTime = sectionStatus.getCreatedAt() == null ? Calendar.getInstance()
                    : sectionStatus.getCreatedAt();

            sectionTime = timeHelper.getRemainingTimeInSeconds( startTime, maxTimeSeconds );
        }

        if ( section != null ) {
            Question question = getNextQuestion( section, student );
            // if question null section is complete
            if ( question == null ) {

                sectionStatus.setComplete( Boolean.TRUE );
                sectionStatusRepository.save( sectionStatus );
                sectionIsComplete = true;
            }
            else {
                questionTransfer = createQuestionTransfer( question, student );
                /** Check if the question after getting transfer you realized the 
                 * question is complete. This happens for comprehension question.
                 */
                if ( questionTransfer.isComprehensionQuestion() && questionTransfer
                        .getQuestionTransfer() == null ) {

                    QuestionStatus questionStatus =
                            questionStatusRepository
                            .findByQuestionAndStudent( question, student );

                    questionStatus.setComplete( Boolean.TRUE );
                    questionStatusRepository.save( questionStatus );
                    question = getNextQuestion( section, student );

                    if ( question == null ) {

                        sectionStatus.setComplete( Boolean.TRUE );
                        sectionStatusRepository.save( sectionStatus );
                        sectionIsComplete = true;
                    }
                    else
                        questionTransfer = createQuestionTransfer( question, student );
                }
            }
        }

        ExamSectionTransfer sectionTransfer = new ExamSectionTransfer( section.getId(), section
                .getName(), section.getDescription(), sectionTime, sectionIsComplete,
                questionTransfer );

        return sectionTransfer;
    }


    private Section getNextSection( ExamToken examToken, Student student ) {

        Set<Section> allSections = examToken.getInvite().getExam().getSections();
        Set<Section> incompleteSections = new HashSet<>();

        for ( Section section : allSections ) {

            SectionStatus sectionStatus =
                    sectionStatusRepository
                    .findBySectionAndStudent( section, student );

            if ( sectionStatus == null || !sectionStatus.getComplete() )
                incompleteSections.add( section );
        }

        if ( incompleteSections.size() > 0 )
            return incompleteSections.iterator().next();

        return null;
    }


    private ExamTimedPer getExamTimedPer( Exam exam ) {

        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;

        ExamTimerType examTimer = examTimerTypeRepository.findById( ExamTimerTypeEnum.TIMED_PER_EXAM
                .getExamTimerTypeId() ).get();

        ExamTimerType sectionTimer = examTimerTypeRepository.findById(
                ExamTimerTypeEnum.TIMED_PER_SECTION.getExamTimerTypeId() ).get();

        ExamTimerType questionTimer = examTimerTypeRepository.findById(
                ExamTimerTypeEnum.TIMED_PER_QUESTION.getExamTimerTypeId() ).get();

        if ( exam.getExamTimerType() == examTimer )
            timedPerExam = Boolean.TRUE;
        else if ( exam.getExamTimerType() == sectionTimer )
            timedPerSection = Boolean.TRUE;
        else if ( exam.getExamTimerType() == questionTimer )
            timedPerQuestion = Boolean.TRUE;

        return new ExamTimedPer( timedPerExam, timedPerSection, timedPerQuestion );
    }


    private ExaminprogressResponse fetchNextSectionAndQuestion( ExamToken examToken,
            ExamTimedPer examTimedPer ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = false;
        boolean examExpired = false;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;
        Exam exam = examToken.getInvite().getExam();
        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
        Invite invite = examToken.getInvite();
        examStartDate = invite.getExamStartDate();
        examStartTime = invite.getExamStartTime();
        pausable = invite.getPausable();

        if ( examTimedPer.timedPerExam ) {

            timedPerExam = Boolean.TRUE;
            Integer maxTimeSeconds = ( int )exam.getDuration().toSeconds();

            if ( !invite.getPausable() ) {
                Calendar startTime = Calendar.getInstance();

                startTime.set( examStartDate.getYear(), examStartDate.getMonthValue() - 1,
                        examStartDate.getDayOfMonth(), examStartTime.getHour(),
                        examStartTime.getMinute(), examStartTime.getSecond() );

                examTime = timeHelper.getRemainingTimeInSeconds( startTime, maxTimeSeconds );
            }
            else {

                Long examTimeSpent = examStatus.getTimeSpent();
                examTime = maxTimeSeconds - examTimeSpent;
            }
        }
        else if ( examTimedPer.timedPerSection ) {
            timedPerSection = Boolean.TRUE;
        }
        else if ( examTimedPer.timedPerQuestion )
            timedPerQuestion = Boolean.TRUE;

        Section section = getNextSection( examToken, student );

        if ( section == null ) {
            examComplete = true;
            examStatus.setComplete( Boolean.TRUE );
            examStatusRepository.save( examStatus );

            return new ExaminprogressResponse(
                    examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                    timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                    examStartDate, examStartTime, examSectionTransfer );
        }
        else {

            examSectionTransfer = createSectionTransfer( section, student, invite );

            if ( examSectionTransfer.isSectionComplete() ) {
                section = getNextSection( examToken, student );

                if ( section == null ) {
                    examComplete = true;
                    examStatus.setComplete( Boolean.TRUE );
                    examStatusRepository.save( examStatus );

                    return new ExaminprogressResponse(
                            examTokenNotFound, examNotFound, examHasStarted, examComplete,
                            examExpired,
                            timedPerExam, timedPerSection, timedPerQuestion, pausable, paused,
                            examTime,
                            examStartDate, examStartTime, examSectionTransfer );
                }
                else {
                    examSectionTransfer = createSectionTransfer( section, student, invite );
                }
            }

            return new ExaminprogressResponse(
                    examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                    timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                    examStartDate, examStartTime, examSectionTransfer );
        }
    }


    private boolean checkIfExamHasExpired( ExamToken examToken,
            ExamStatus examStatus,
            ExamTimedPer examTimedPer ) {

        boolean expired = false;
        Invite invite = examToken.getInvite();
        Exam exam = invite.getExam();
        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Integer maxTimeSeconds = ( int )exam.getTotalExamTime().getSeconds();

        if ( invite.getExamStartTime() != null ) {

            LocalDate startDate = invite.getExamStartDate();
            Calendar startTime = Calendar.getInstance();
            LocalTime examStartTime = invite.getExamStartTime();

            startTime.set( startDate.getYear(), startDate.getMonthValue() - 1,
                    startDate.getDayOfMonth(), examStartTime.getHour(),
                    examStartTime.getMinute(), examStartTime.getSecond() );

            expired = timeHelper.hasExpired( startTime, maxTimeSeconds );
        }
        else {

            if ( examTimedPer.timedPerExam ) {

                Calendar startTime = examStatus.getCreatedAt();
                expired = timeHelper.hasExpired( startTime, maxTimeSeconds );
            }
            else if ( examTimedPer.timedPerSection ) {

                Calendar startTime = Calendar.getInstance();
                Set<SectionStatus> sectionStatuses = sectionStatusRepository
                        .findByStudentAndSectionExam( student, exam );

                for ( SectionStatus sectionStatus : sectionStatuses ) {

                    if ( sectionStatus.getCreatedAt().compareTo( startTime ) < 0 ) {

                        startTime = sectionStatus.getCreatedAt();
                    }
                }

                expired = timeHelper.hasExpired( startTime, maxTimeSeconds );
            }
            else {

                Calendar startTime = Calendar.getInstance();
                Set<QuestionStatus> questionStatuses = questionStatusRepository
                        .findByStudentAndQuestionSectionExam( student, exam );

                for ( QuestionStatus questionStatus : questionStatuses ) {

                    if ( questionStatus.getCreatedAt().compareTo( startTime ) < 0 ) {

                        startTime = questionStatus.getCreatedAt();
                    }
                }

                expired = timeHelper.hasExpired( startTime, maxTimeSeconds );
            }
        }

        return expired;
    }


    private boolean checkIfPausableExamExpired( Invite invite ) {

        LocalDate endDate = invite.getExamEndDate();
        LocalDate today = LocalDate.now();

        if ( today.isAfter( endDate ) ) {
            return true;
        }
        return false;
    }


    @Transactional
    @Override
    public ExaminprogressResponse getExamProgress( Long examTokenId ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = false;
        boolean examExpired = false;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;

        ExaminprogressResponse response = new ExaminprogressResponse(
                examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                examStartDate, examStartTime, examSectionTransfer );

        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();

        if ( examToken == null ) {
            examTokenNotFound = true;
            return response;
        }

        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Invite invite = examToken.getInvite();
        Exam exam = invite.getExam();

        if ( exam == null ) {
            examNotFound = true;
            return response;
        }

        examStartDate = invite.getExamStartDate();
        examStartTime = invite.getExamStartTime();
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if ( examStartTime != null && examStartTime.isAfter( now ) &&
                ( examStartDate.isEqual( today ) || examStartDate.isAfter( today ) ) ) {

            examHasStarted = false;

            return new ExaminprogressResponse(
                    examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                    timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                    examStartDate, examStartTime, examSectionTransfer );
        }
        else if ( examStartTime == null && examStartDate.isAfter( today ) ) {

            examHasStarted = false;

            return new ExaminprogressResponse(
                    examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                    timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                    examStartDate, examStartTime, examSectionTransfer );
        }

        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
        pausable = examToken.getInvite().getPausable();
        examTime = exam.getDuration() != null ? exam.getDuration().toSeconds() : null;
        ExamTimedPer examTimedPer = getExamTimedPer( exam );

        if ( examTimedPer.timedPerExam ) {
            timedPerExam = true;
        }
        else if ( examTimedPer.timedPerQuestion ) {
            timedPerQuestion = true;
        }
        else {
            timedPerSection = true;
        }

        // Check if the token belongs to the logged in user
        User user = loggedInCredentialsHelper.getLoggedInUser();
        if ( !user.getEmail().equals( examToken.getEmail() ) )
            return response;

        if ( examStatus != null && examStatus.getComplete() ) {

            examComplete = true;

            return new ExaminprogressResponse(
                    examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                    timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                    examStartDate, examStartTime, examSectionTransfer );
        }
        else {

            if ( examStatus == null ) {
                examStatus = new ExamStatus();
                examStatus.setComplete( Boolean.FALSE );
                examStatus.setStarted( Boolean.TRUE );
                examStatus.setPaused( Boolean.FALSE );
                examStatus.setTimeSpent( 0l );
                examStatus.setExam( exam );
                examStatus.setStudent( student );
                examStatusRepository.save( examStatus );
            }

            /**
             * Check if exam was paused previously
             */
            if ( examStatus.getPaused() ) {

                boolean expired = checkIfPausableExamExpired( invite );

                if ( expired ) {

                    examExpired = true;
                    examStatus.setComplete( Boolean.TRUE );
                    examStatusRepository.save( examStatus );

                    return new ExaminprogressResponse( examTokenNotFound, examNotFound,
                            examHasStarted, examComplete, examExpired,
                            timedPerExam, timedPerSection, timedPerQuestion, pausable, paused,
                            examTime,
                            examStartDate, examStartTime, examSectionTransfer );
                }
                else {

                    examStatus.setResumedAt( Calendar.getInstance() );
                    examStatusRepository.save( examStatus );
                    response = fetchNextSectionAndQuestion( examToken, examTimedPer );
                    return response;
                }
            }
            else {

                if ( !invite.getPausable() ) {
                    boolean expired = checkIfExamHasExpired( examToken, examStatus, examTimedPer );

                    if ( expired ) {

                        examExpired = true;
                        examStatus.setComplete( Boolean.TRUE );
                        examStatusRepository.save( examStatus );

                        return new ExaminprogressResponse( examTokenNotFound, examNotFound,
                                examHasStarted, examComplete, examExpired,
                                timedPerExam, timedPerSection, timedPerQuestion, pausable, paused,
                                examTime, examStartDate, examStartTime, examSectionTransfer );
                    }
                    else {

                        response = fetchNextSectionAndQuestion( examToken, examTimedPer );
                        return response;
                    }
                }
                else {

                    boolean expired = checkIfPausableExamExpired( invite );

                    if ( expired ) {

                        examExpired = true;
                        examStatus.setComplete( Boolean.TRUE );
                        examStatusRepository.save( examStatus );

                        return new ExaminprogressResponse( examTokenNotFound, examNotFound,
                                examHasStarted, examComplete, examExpired, timedPerExam,
                                timedPerSection, timedPerQuestion, pausable, paused, examTime,
                                examStartDate, examStartTime, examSectionTransfer );
                    }
                    else {

                        response = fetchNextSectionAndQuestion( examToken, examTimedPer );
                        return response;
                    }
                }

            }
        }
    }



    @Transactional
    @Override
    public ExaminprogressResponse saveAnswer( AnswerRequest request ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = false;
        boolean examExpired = false;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;
        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Question question = questionRepository.findById( request.getQuestionId() ).get();
        Section section = question.getSection();
        Exam exam = section.getExam();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );

        SectionStatus sectionStatus = sectionStatusRepository
                .findBySectionAndStudent( section, student );

        QuestionStatus questionStatus = questionStatusRepository
                .findByQuestionAndStudent( question, student );

        ExamTimedPer examTimedPer = getExamTimedPer( exam );
        Boolean hasExpired = true;

        if ( examTimedPer.timedPerExam ) {

            Calendar startTime = examStatus.getCreatedAt();
            Integer maxTimeSeconds = ( int )exam.getDuration().getSeconds();
            hasExpired = timeHelper.hasExpired( startTime, maxTimeSeconds );

            if ( hasExpired ) {
                examStatus.setComplete( Boolean.TRUE );
                examStatusRepository.save( examStatus );
            }
        }
        else if ( examTimedPer.timedPerSection ) {

            Calendar startTime = sectionStatus.getCreatedAt();
            Integer maxTimeSeconds = ( int )section.getDuration().getSeconds();
            hasExpired = timeHelper.hasExpired( startTime, maxTimeSeconds );

            if ( hasExpired ) {
                sectionStatus.setComplete( Boolean.TRUE );
                sectionStatusRepository.save( sectionStatus );
            }
        }
        else {
            Calendar startTime = questionStatus.getCreatedAt();
            Integer maxTimeSeconds = ( int )question.getDuration().getSeconds();
            hasExpired = timeHelper.hasExpired( startTime, maxTimeSeconds );
        }

        if ( !hasExpired ) {
            for ( Long answerId : request.getAnswerIds() ) {
                Answer answer = answerRepository.findById( answerId ).get();
                answer.setStudent( student );
                answerRepository.save( answer );
            }
            if ( request.getAnswerText() != null ) {
                Answer answer = new Answer();
                answer.setAnswerText( request.getAnswerText() );
                answer.setAnswerType( question.getAnswerType() );
                answer.setQuestion( question );
                answer.setStudent( student );
                answerRepository.save( answer );
            }
        }

        questionStatus.setComplete( Boolean.TRUE );
        questionStatusRepository.save( questionStatus );

        Long now = Calendar.getInstance().getTimeInMillis() / 1000;
        Long createdAt = questionStatus.getCreatedAt().getTimeInMillis() / 1000;
        Long timeSpent = now - createdAt;
        Long examTimeSpent = examStatus.getTimeSpent() + timeSpent;
        examStatus.setTimeSpent( examTimeSpent );
        examStatusRepository.save( examStatus );
        sectionStatus.setTimeSpent( examTimeSpent );
        sectionStatusRepository.save( sectionStatus );
        ExaminprogressResponse response;

        if ( request.getPause() ) {
            ExamToken examToken = examTokenRepository.findById( request.getExamTokenId() ).get();
            Boolean examPausable = examToken.getInvite().getPausable();

            if ( examPausable ) {

                pausable = true;
                paused = true;
                examStatus.setPaused( Boolean.TRUE );
                examStatus.setPausedAt( Calendar.getInstance() );
                examStatusRepository.save( examStatus );

                response = new ExaminprogressResponse(
                        examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                        timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                        examStartDate, examStartTime, examSectionTransfer );
            }
            else {
                response = getExamProgress( request.getExamTokenId() );
            }
        }
        else {
            response = getExamProgress( request.getExamTokenId() );
        }

        return response;
    }


    @Override
    @Transactional
    public ExaminprogressResponse skipQuestion( SkipQuestionRequest request ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = false;
        boolean examExpired = false;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;
        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Question question = questionRepository.findById( request.getQuestionId() ).get();
        Exam exam = question.getSection().getExam();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );

        QuestionStatus questionStatus = questionStatusRepository
                .findByQuestionAndStudent( question, student );

        questionStatus.setComplete( Boolean.TRUE );
        questionStatusRepository.save( questionStatus );
        ExaminprogressResponse response;

        if ( request.getPause() ) {
            ExamToken examToken = examTokenRepository.findById( request.getExamTokenId() ).get();
            Boolean examPausable = examToken.getInvite().getPausable();

            if ( examPausable ) {

                pausable = true;
                paused = true;
                examStatus.setPaused( Boolean.TRUE );
                examStatus.setPausedAt( Calendar.getInstance() );
                examStatusRepository.save( examStatus );

                response = new ExaminprogressResponse(
                        examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                        timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                        examStartDate, examStartTime, examSectionTransfer );
            }
            else {
                response = getExamProgress( request.getExamTokenId() );
            }
        }
        else {
            response = getExamProgress( request.getExamTokenId() );
        }

        return response;
    }


    @Transactional
    @Override
    public ExaminprogressResponse skipSection( SkipSectionRequest request ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = false;
        boolean examExpired = false;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;
        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Section section = sectionRepository.findById( request.getSectionId() ).get();
        Exam exam = section.getExam();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );

        SectionStatus sectionStatus = sectionStatusRepository.findBySectionAndStudent( section,
                student );

        sectionStatus.setComplete( Boolean.TRUE );
        sectionStatusRepository.save( sectionStatus );
        ExaminprogressResponse response;

        if ( request.getPause() ) {
            ExamToken examToken = examTokenRepository.findById( request.getExamTokenId() ).get();
            Boolean examPausable = examToken.getInvite().getPausable();

            if ( examPausable ) {

                pausable = true;
                paused = true;
                examStatus.setPaused( Boolean.TRUE );
                examStatus.setPausedAt( Calendar.getInstance() );
                examStatusRepository.save( examStatus );

                response = new ExaminprogressResponse(
                        examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                        timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                        examStartDate, examStartTime, examSectionTransfer );
            }
            else {
                response = getExamProgress( request.getExamTokenId() );
            }
        }
        else {
            response = getExamProgress( request.getExamTokenId() );
        }

        return response;
    }


    @Override
    public ExaminprogressResponse terminateExam( Long examTokenId ) {

        boolean examTokenNotFound = false;
        boolean examNotFound = false;
        boolean examHasStarted = true;
        boolean examComplete = true;
        boolean examExpired = true;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;
        boolean pausable = false;
        boolean paused = false;
        Long examTime = null;
        LocalDate examStartDate = null;
        LocalTime examStartTime = null;
        ExamSectionTransfer examSectionTransfer = null;

        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        Exam exam = examToken.getInvite().getExam();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
        examStatus.setComplete( Boolean.TRUE );
        examStatusRepository.save( examStatus );

        return new ExaminprogressResponse(
                examTokenNotFound, examNotFound, examHasStarted, examComplete, examExpired,
                timedPerExam, timedPerSection, timedPerQuestion, pausable, paused, examTime,
                examStartDate, examStartTime, examSectionTransfer );
    }
}

class ExamTimedPer {

    boolean timedPerExam;
    boolean timedPerSection;
    boolean timedPerQuestion;

    public ExamTimedPer(
            boolean timedPerExam,
            boolean timedPerSection,
            boolean timedPerQuestion ) {

        super();
        this.timedPerExam = timedPerExam;
        this.timedPerSection = timedPerSection;
        this.timedPerQuestion = timedPerQuestion;
    }


    public boolean isTimedPerExam() {

        return timedPerExam;
    }


    public void setTimedPerExam( boolean timedPerExam ) {

        this.timedPerExam = timedPerExam;
    }


    public boolean isTimedPerSection() {

        return timedPerSection;
    }


    public void setTimedPerSection( boolean timedPerSection ) {

        this.timedPerSection = timedPerSection;
    }


    public boolean isTimedPerQuestion() {

        return timedPerQuestion;
    }


    public void setTimedPerQuestion( boolean timedPerQuestion ) {

        this.timedPerQuestion = timedPerQuestion;
    }
}
