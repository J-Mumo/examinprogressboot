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
package com.joel.examinprogress.service.teacher.results.finalizescoring;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.QuestionTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerTypeEnum;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.domain.student.QuestionStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.result.ResultHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.student.ExamStatusRepository;
import com.joel.examinprogress.repository.student.QuestionStatusRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   Oct 15, 2020
 */
@Service
public class FinalizeScoringServiceImpl implements FinalizeScoringService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResultHelper resultHelper;

    private FinalizeScore createFinalizeScore( Question question, Student student ) {

        Long questionId = question.getId();
        String questionText = question.getQuestionText();
        Integer questionMaxPoints = question.getScore();

        QuestionStatus questionStatus = questionStatusRepository.findByQuestionAndStudent(
                question, student );

        if ( questionStatus == null ) {

            questionStatus = new QuestionStatus();
            questionStatus.setComplete( Boolean.TRUE );
            questionStatus.setFetched( Boolean.TRUE );
            questionStatus.setQuestion( question );
            questionStatus.setStudent( student );
            questionStatusRepository.save( questionStatus );
        }

        String studentAnswer = questionStatus.getTextAnswer();
        return new FinalizeScore(questionId, questionText, questionMaxPoints, studentAnswer);
    }


    private FinalizeScore createFinalizeScore( Exam exam, Student student ) {

        Set<Question> questions = new HashSet<>();
        Set<Section> sections = exam.getSections();
        FinalizeScore finalizeScore = null;
        Long textAnswerTypeId = AnswerTypeEnum.TEXT_ANSWER.getAnswerTypeId();

        Long comprehensionQuestionTypeId = QuestionTypeEnum.COMPREHENSION_QUESTION
                .getQuestionTypeId();

        for ( Section section : sections ) {

            questions.addAll( section.getQuestions() );
        }

        for ( Question question : questions ) {

            if ( question.getQuestionType().getId() != comprehensionQuestionTypeId &&
                    question.getAnswerType().getId() == textAnswerTypeId ) {

                Result questionResult = resultRepository.findByQuestionAndStudent( question,
                        student );

                if ( questionResult.getPointScore() == null ) {

                    finalizeScore = createFinalizeScore( question, student );
                    break;
                }
            }
        }

        return finalizeScore;
    }


    @Transactional
    @Override
    public FinalizeScoringInitialData getInitialData( FinalizeScoringRequestInitialData request ) {

        Long examId = request.getExamId();
        Long studentId = request.getStudentId();
        Exam exam = examRepository.findById( examId ).get();
        Student student = studentRepository.findById( studentId ).get();
        User studentUser = student.getUser();
        String studentName = studentUser.getFirstName() + " " + studentUser.getLastName();
        FinalizeScore finalizeScore = createFinalizeScore( exam, student );
        Boolean scoringComplete = finalizeScore != null ? Boolean.FALSE : Boolean.TRUE;

        if ( scoringComplete ) {

            ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
            examStatus.setScoringComplete( Boolean.TRUE );
            examStatusRepository.save( examStatus );
        }

        return new FinalizeScoringInitialData( studentName, scoringComplete, finalizeScore );
    }


    @Transactional
    @Override
    public SaveResponse save( FinalizeScoringRequest request ) {

        Question question = questionRepository.findById( request.getQuestionId() ).get();
        Student student = studentRepository.findById( request.getStudentId() ).get();
        resultHelper.updateResult( question, student, request.getPointsEarned() );
        return new SaveResponse( Boolean.TRUE, null );
    }

}
