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
package com.joel.examinprogress.service.teacher.exam.section.question.add;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.MultipleChoiceAnswerRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   15th June, 2020
 */
@Service
public class AddQuestionServiceImpl implements AddQuestionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    MultipleChoiceAnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Transactional
    @Override
    public SaveResponse save( AddQuestionRequest request ) {

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        Question question = new Question();
        question.setQuestionText( request.getQuestionText() );
        question.setScore( request.getScore() );
        question.setSection( section );
        questionRepository.save( question );

        Set<MultipleChoiceAnswer> multipleChoiceAnswers = new HashSet<MultipleChoiceAnswer>();

        for ( AddMultipleChoiceQuestionAnswerRequest answerRequest : request
                .getAddMultipleChoiceQuestionAnswerRequests() ) {
            MultipleChoiceAnswer answer = new MultipleChoiceAnswer();
            answer.setAnswerText( answerRequest.getAnswerText() );
            answer.setQuestion( question );
            answerRepository.save( answer );

            if ( answerRequest.isCorrect() ) {
                multipleChoiceAnswers.add( answer );
            }
        }

        question.setMultipleChoiceAnswers( multipleChoiceAnswers );
        questionRepository.save( question );

        return new SaveResponse( true, null );
    }

}
