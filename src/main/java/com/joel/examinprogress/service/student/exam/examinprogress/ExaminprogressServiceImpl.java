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
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.student.QuestionComplete;
import com.joel.examinprogress.domain.student.SectionComplete;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.helper.time.TimeHelper;
import com.joel.examinprogress.repository.exam.ExamTimerTypeRepository;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.repository.student.QuestionCompleteRepository;
import com.joel.examinprogress.repository.student.SectionCompleteRepository;

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
    private SectionCompleteRepository sectionCompleteRepository;

    @Autowired
    private QuestionCompleteRepository questionCompleteRepository;

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
        Set<Question> incompleteQuestions = new HashSet<>();

        for ( Question subQuestion : allQuestions ) {

            QuestionComplete questionComplete = questionCompleteRepository
                    .findByQuestionAndStudent( subQuestion, student );

            if ( questionComplete == null || !questionComplete.getComplete() )
                incompleteQuestions.add( subQuestion );

            if ( incompleteQuestions.size() > 0 )
                return incompleteQuestions.iterator().next();

        }
        return null;
    }


    private ExamQuestionTransfer createComprehensionSubQuestionTransfer( Question question,
            Student student ) {

        Long questionTime = question.getDuration() != null ? question.getDuration().toSeconds()
                : null;

        QuestionComplete questionComplete = questionCompleteRepository
                .findByQuestionAndStudent( question, student );

        if ( questionComplete == null ) {
            questionComplete = new QuestionComplete();
            questionComplete.setQuestion( question );
            questionComplete.setStudent( student );
        }

        questionComplete.setComplete( Boolean.FALSE );
        questionCompleteRepository.save( questionComplete );

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

        QuestionComplete questionComplete = questionCompleteRepository
                .findByQuestionAndStudent( question, student );

        if ( questionComplete == null ) {
            questionComplete = new QuestionComplete();
            questionComplete.setQuestion( question );
            questionComplete.setStudent( student );
        }

        questionComplete.setComplete( Boolean.FALSE );
        questionCompleteRepository.save( questionComplete );

        AnswerTransfer[] answerTransfers = createAnswerTransfers( question );
        boolean comprehensionQuestion = question.getQuestions().size() > 0 ? true : false;
        ExamQuestionTransfer questionTransfer = null;
        if ( comprehensionQuestion ) {
            Question subQuestion = getNextComprehensionSubQuestion( question, student );
            if ( subQuestion == null ) {
                questionComplete.setComplete( Boolean.TRUE );
                questionCompleteRepository.save( questionComplete );
            }
            else
                questionTransfer = createComprehensionSubQuestionTransfer( subQuestion, student );
        }
        ExamQuestionTransfer examQuestionTransfer = new ExamQuestionTransfer( question.getId(),
                comprehensionQuestion, question.getQuestionText(), questionTime, questionTransfer,
                question.getAnswerType().getName(), answerTransfers );

        return examQuestionTransfer;
    }


    private Question getNextQuestion( Section section, Student student ) {

        Set<Question> allQuestions = section.getQuestions();
        Set<Question> incompleteQuestions = new HashSet<>();

        for ( Question question : allQuestions ) {
            if ( question.getQuestion() == null ) {
                QuestionComplete questionComplete = questionCompleteRepository
                        .findByQuestionAndStudent( question, student );

                if ( questionComplete == null || !questionComplete.getComplete() )
                    incompleteQuestions.add( question );

                if ( incompleteQuestions.size() > 0 )
                    return incompleteQuestions.iterator().next();

            }
        }
        return null;
    }


    private ExamSectionTransfer createSectionTransfer( Section section, Student student ) {

        ExamQuestionTransfer questionTransfer = null;
        boolean sectionIsComplete = false;
        Long sectionTime = section.getDuration() != null ? section.getDuration().toSeconds() : null;
        SectionComplete sectionComplete = sectionCompleteRepository
                .findBySectionAndStudent( section, student );

        if ( section != null ) {
            Question question = getNextQuestion( section, student );
            // if question null section is complete
            if ( question == null ) {
                if ( sectionComplete == null ) {
                    sectionComplete = new SectionComplete();
                    sectionComplete.setSection( section );
                    sectionComplete.setStudent( student );
                }

                sectionComplete.setComplete( Boolean.TRUE );
                sectionCompleteRepository.save( sectionComplete );
                sectionIsComplete = true;
            }
            else {
                questionTransfer = createQuestionTransfer( question, student );
                /** Check if the question after getting transfer you realized the 
                 * question is complete. This happens for comprehension question.
                 */
                if ( questionTransfer.isComprehensionQuestion() && questionTransfer
                        .getQuestionTransfer() == null ) {

                    QuestionComplete questionComplete = questionCompleteRepository
                            .findByQuestionAndStudent( question, student );

                    questionComplete.setComplete( Boolean.TRUE );
                    questionCompleteRepository.save( questionComplete );
                    question = getNextQuestion( section, student );
                    if ( question == null ) {
                        if ( sectionComplete == null ) {
                            sectionComplete = new SectionComplete();
                            sectionComplete.setSection( section );
                            sectionComplete.setStudent( student );
                        }

                        sectionComplete.setComplete( Boolean.TRUE );
                        sectionCompleteRepository.save( sectionComplete );
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
            SectionComplete sectionComplete = sectionCompleteRepository
                    .findBySectionAndStudent( section, student );

            if ( sectionComplete == null || !sectionComplete.getComplete() )
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


    @Transactional
    @Override
    public ExaminprogressResponse getExamProgress( Long examTokenId ) {

        ExaminprogressResponse response = null;
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        if ( examToken == null )
            return response;
        else if ( examToken.getStartedExamAt() == null ) {
            examToken.setStartedExamAt( Calendar.getInstance() );
            examTokenRepository.save( examToken );
        }

        Exam exam = examToken.getInvite().getExam();
        if ( exam == null )
            return response;

        Boolean examComplete = false;
        Boolean timedPerExam = false;
        Boolean timedPerSection = false;
        Boolean timedPerQuestion = false;
        ExamTimedPer examTimedPer = getExamTimedPer( exam );
        if ( examTimedPer.timedPerExam )
            timedPerExam = Boolean.TRUE;
        else if ( examTimedPer.timedPerSection )
            timedPerSection = Boolean.TRUE;
        else if ( examTimedPer.timedPerQuestion )
            timedPerQuestion = Boolean.TRUE;

        Long examTime = exam.getDuration() != null ? exam.getDuration().toSeconds() : null;

        // Check if the token belongs to the logged in user
        User user = loggedInCredentialsHelper.getLoggedInUser();
        if ( !user.getEmail().equals( examToken.getEmail() ) )
            return response;

        if ( examToken.getExamComplete() )
            return new ExaminprogressResponse( null, true, timedPerExam, timedPerSection,
                    timedPerQuestion, examTime );
        else {
            Student student = examToken.getStudent();
            Section section = getNextSection( examToken, student );

            if ( section == null ) {
                examComplete = true;
                examToken.setExamComplete( Boolean.TRUE );
                examTokenRepository.save( examToken );

                return new ExaminprogressResponse( null, examComplete, timedPerExam,
                        timedPerSection, timedPerQuestion, examTime );
            }
            else {
                ExamSectionTransfer sectionTransfer = createSectionTransfer( section, student );

                if ( sectionTransfer.isSectionComplete() ) {
                    section = getNextSection( examToken, student );

                    if ( section == null ) {
                        examComplete = true;
                        examToken.setExamComplete( Boolean.TRUE );
                        examTokenRepository.save( examToken );

                        return new ExaminprogressResponse( null, examComplete, timedPerExam,
                                timedPerSection, timedPerQuestion, examTime );
                    }
                    else {
                        sectionTransfer = createSectionTransfer( section, student );
                    }
                }
                return new ExaminprogressResponse( sectionTransfer, examComplete, timedPerExam,
                        timedPerSection, timedPerQuestion, examTime );
            }
        }
    }


    @Transactional
    @Override
    public ExaminprogressResponse saveAnswer( AnswerRequest request ) {

        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Question question = questionRepository.findById( request.getQuestionId() ).get();
        Section section = question.getSection();
        Exam exam = section.getExam();

        QuestionComplete questionComplete = questionCompleteRepository.findByQuestionAndStudent(
                question, student );

        ExamTimedPer examTimedPer = getExamTimedPer( exam );
        Boolean hasExpired = true;

        if ( examTimedPer.timedPerExam ) {

            ExamToken examToken = examTokenRepository.findById( request.getExamTokenId() ).get();
            Calendar startTime = examToken.getStartedExamAt();
            Integer maxTimeSeconds = ( int )exam.getDuration().getSeconds();
            hasExpired = timeHelper.hasExpired( startTime, maxTimeSeconds );

            if ( hasExpired ) {
                examToken.setExamComplete( Boolean.TRUE );
                examTokenRepository.save( examToken );
            }
        }
        else if ( examTimedPer.timedPerSection ) {

            SectionComplete sectionComplete = sectionCompleteRepository.findBySectionAndStudent(
                    section, student );

            Calendar startTime = sectionComplete.getCreatedAt();
            Integer maxTimeSeconds = ( int )section.getDuration().getSeconds();
            hasExpired = timeHelper.hasExpired( startTime, maxTimeSeconds );

            if ( hasExpired ) {
                sectionComplete.setComplete( Boolean.TRUE );
                sectionCompleteRepository.save( sectionComplete );
            }
        }
        else {
            Calendar startTime = questionComplete.getCreatedAt();
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

        questionComplete.setComplete( Boolean.TRUE );
        questionCompleteRepository.save( questionComplete );

        ExaminprogressResponse response = getExamProgress( request.getExamTokenId() );
        return response;
    }


    @Override
    @Transactional
    public ExaminprogressResponse skipQuestion( SkipQuestionRequest request ) {

        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        Question question = questionRepository.findById( request.getQuestionId() ).get();

        QuestionComplete questionComplete = questionCompleteRepository.findByQuestionAndStudent(
                question, student );

        questionComplete.setComplete( Boolean.TRUE );
        questionCompleteRepository.save( questionComplete );

        ExaminprogressResponse response = getExamProgress( request.getExamTokenId() );
        return response;
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
