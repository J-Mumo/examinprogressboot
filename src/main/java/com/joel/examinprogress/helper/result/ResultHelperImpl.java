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
package com.joel.examinprogress.helper.result;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.result.ResultType;
import com.joel.examinprogress.domain.exam.result.ResultTypeEnum;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerTypeEnum;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.exam.results.ResultTypeRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;

/**
 * @author Joel Mumo
 * @date   Sep 18, 2020
 */
@Service
public class ResultHelperImpl implements ResultHelper {

    @Autowired
    private ResultTypeRepository resultTypeRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private Result updateQuestionResult( Question question, Student student ) {

        Long questionResultTypeId = ResultTypeEnum.QUESTION_RESULT.getResultTypeId();
        ResultType questionResultType = resultTypeRepository.findById( questionResultTypeId ).get();
        Result questionResult = new Result();
        questionResult.setTotalScore( question.getScore() );
        questionResult.setStudent( student );
        questionResult.setQuestion( question );
        questionResult.setResultType( questionResultType );
        resultRepository.save( questionResult );
        return questionResult;
    }


    private Result updateSectionResult( Question question, Student student ) {

        Long sectionResultTypeId = ResultTypeEnum.SECTION_RESULT.getResultTypeId();
        ResultType sectionResultType = resultTypeRepository.findById( sectionResultTypeId ).get();
        Section section = question.getSection();
        Result sectionResult = resultRepository.findBySectionAndStudent( section, student );

        if ( sectionResult == null ) {
            sectionResult = new Result();
            sectionResult.setPointScore( 0 );
            sectionResult.setPercentScore( 0f );
            sectionResult.setTotalScore( 0 );
        }

        sectionResult.setTotalScore( sectionResult.getTotalScore() + question.getScore() );
        sectionResult.setStudent( student );
        sectionResult.setSection( section );
        sectionResult.setResultType( sectionResultType );
        resultRepository.save( sectionResult );
        return sectionResult;
    }


    private Result updateExamResult( Question question, Student student ) {

        Long examResultTypeId = ResultTypeEnum.EXAM_RESULT.getResultTypeId();
        ResultType examResultType = resultTypeRepository.findById( examResultTypeId ).get();
        Exam exam = question.getSection().getExam();
        Result examResult = resultRepository.findByExamAndStudent( exam, student );

        if ( examResult == null ) {
            examResult = new Result();
            examResult.setPointScore( 0 );
            examResult.setPercentScore( 0f );
            examResult.setTotalScore( 0 );
        }

        examResult.setTotalScore( examResult.getTotalScore() + question.getScore() );
        examResult.setStudent( student );
        examResult.setExam( exam );
        examResult.setResultType( examResultType );
        resultRepository.save( examResult );

        return examResult;
    }


    @Override
    public void updateResult( Question question, Student student ) {
        
        Long answerTypeId = question.getAnswerType().getId();
        Long textAnswerTypeId = AnswerTypeEnum.TEXT_ANSWER.getAnswerTypeId();
        
        if (answerTypeId != textAnswerTypeId) {
            
            Boolean studentAnsweredQuestionCorrectly = true;
            Set<Answer> correctAnswers = question.getCorrectAnswers();
            List<Answer> studentAnswers = answerRepository
                    .findByStudentAndQuestion( student, question );
    
            for(Answer correctAnswer : correctAnswers) {
                for (Answer studentAnswer : studentAnswers) {
                    
                    if ( studentAnswer != correctAnswer ) {
                        studentAnsweredQuestionCorrectly = false;
                    }
                }
            }
            
            Result questionResult = updateQuestionResult( question, student );
            Result sectionResult = updateSectionResult( question, student );
            Result examResult = updateExamResult( question, student );

            if ( studentAnsweredQuestionCorrectly ) {

                Integer score = question.getScore();
                questionResult.setPointScore( score );
                questionResult.setPercentScore( 100f );
                sectionResult.setPointScore( sectionResult.getPointScore() + score );
                examResult.setPointScore( examResult.getPointScore() + score );

            }

            Float sectionPercent = Float.valueOf( sectionResult.getPointScore() ) /
                    Float.valueOf( sectionResult.getTotalScore() ) * 100;

            Float examPercent = Float.valueOf( examResult.getPointScore() ) /
                    Float.valueOf( examResult.getTotalScore() ) * 100;

            sectionResult.setPercentScore( sectionPercent );
            examResult.setPercentScore( examPercent );

            resultRepository.save( questionResult );
            resultRepository.save( sectionResult );
            resultRepository.save( examResult );
        }
    }
}
