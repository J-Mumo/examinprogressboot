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

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.student.QuestionComplete;
import com.joel.examinprogress.domain.student.SectionComplete;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
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


    private ExamQuestionTransfer createQuestionTransfer( Question question, Student student ) {

        AnswerTransfer[] answerTransfers = createAnswerTransfers( question );
        boolean comprehensionQuestion = question.getQuestions().size() > 0 ? true : false;
        ExamQuestionTransfer examQuestionTransfer = new ExamQuestionTransfer( question.getId(),
                comprehensionQuestion, question.getQuestionText(), question.getAnswerType()
                        .getName(), answerTransfers );

        QuestionComplete questionComplete = questionCompleteRepository
                .findByQuestionAndStudent( question, student );

        if ( questionComplete == null ) {
            questionComplete = new QuestionComplete();
            questionComplete.setQuestion( question );
            questionComplete.setStudent( student );
        }

        questionComplete.setComplete( Boolean.FALSE );
        questionCompleteRepository.save( questionComplete );

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
        if ( section != null ) {
            Question question = getNextQuestion( section, student );
            // if question null section is complete
            if ( question == null ) {
                SectionComplete sectionComplete = sectionCompleteRepository
                        .findBySectionAndStudent( section, student );

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

        ExamSectionTransfer sectionTransfer = new ExamSectionTransfer( section.getId(), section
                .getName(), section.getDescription(), sectionIsComplete, questionTransfer );

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


    @Transactional
    @Override
    public ExaminprogressResponse getExamProgress( Long examTokenId ) {

        ExaminprogressResponse response = null;
        boolean examComplete = false;
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        if ( examToken == null )
            return response;

        Exam exam = examToken.getInvite().getExam();
        if ( exam == null )
            return response;

        // Check if the token belongs to the logged in user
        User user = loggedInCredentialsHelper.getLoggedInUser();
        if ( !user.getEmail().equals( examToken.getEmail() ) )
            return response;

        if ( examToken.getExamComplete() )
            return new ExaminprogressResponse( null, true );
        else {
            Student student = examToken.getStudent();
            Section section = getNextSection( examToken, student );

            if ( section == null ) {
                examComplete = true;
                examToken.setExamComplete( Boolean.TRUE );
                examTokenRepository.save( examToken );

                return new ExaminprogressResponse( null, examComplete );
            }
            else {
                ExamSectionTransfer sectionTransfer = createSectionTransfer( section, student );

                if ( sectionTransfer.isSectionComplete() ) {
                    section = getNextSection( examToken, student );

                    if ( section == null ) {
                        examComplete = true;
                        examToken.setExamComplete( Boolean.TRUE );
                        examTokenRepository.save( examToken );

                        return new ExaminprogressResponse( null, examComplete );
                    }
                    else {
                        sectionTransfer = createSectionTransfer( section, student );
                    }
                }
                return new ExaminprogressResponse( sectionTransfer, examComplete );
            }
        }
    }


    @Transactional
    @Override
    public ExaminprogressResponse saveMultipleChoiceAnswer( MultipleChoiceAnswerRequest request ) {

        Student student = loggedInCredentialsHelper.getLoggedInUser().getStudent();
        for ( Long answerId : request.getAnswerIds() ) {
            Answer answer = answerRepository.findById( answerId ).get();
            answer.setStudent( student );
            answerRepository.save( answer );
        }

        Question question = questionRepository.findById( request.getQuestionId() ).get();
        QuestionComplete questionComplete = questionCompleteRepository.findByQuestionAndStudent(
                question, student );

        questionComplete.setComplete( Boolean.TRUE );
        questionCompleteRepository.save( questionComplete );

        ExaminprogressResponse response = getExamProgress( request.getExamTokenId() );
        return response;
    }
}
