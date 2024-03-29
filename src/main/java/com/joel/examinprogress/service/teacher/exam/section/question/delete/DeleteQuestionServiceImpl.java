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
package com.joel.examinprogress.service.teacher.exam.section.question.delete;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.student.QuestionStatus;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.repository.student.QuestionStatusRepository;
import com.joel.examinprogress.service.shared.DeleteResponse;

/**
 * @author Joel Mumo
 * @date   31st July, 2020
 */
@Service
public class DeleteQuestionServiceImpl implements DeleteQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Transactional
    @Override
    public DeleteResponse deleteQuestion( Long questionId ) {

        Question question = questionRepository.findById( questionId ).get();

        Set<Question> questions = question.getQuestions();
        for ( Question aQuestion : questions ) {
            Set<Answer> answers = answerRepository.findByQuestion( aQuestion );
            answerRepository.deleteAll( answers );
            List<Result> results = resultRepository.findByQuestion( aQuestion );
            resultRepository.deleteAll( results );

            Set<QuestionStatus> questionStatuss = questionStatusRepository.findByQuestion(
                    aQuestion );

            questionStatusRepository.deleteAll( questionStatuss );
        }

        Set<Answer> answers = answerRepository.findByQuestion( question );
        Set<QuestionStatus> questionStatuss = questionStatusRepository.findByQuestion( question );
        List<Result> results = resultRepository.findByQuestion( question );
        resultRepository.deleteAll( results );
        questionStatusRepository.deleteAll( questionStatuss );
        answerRepository.deleteAll( answers );
        questionRepository.deleteAll( questions );
        questionRepository.delete( question );

        return new DeleteResponse( true, null );
    }

}
