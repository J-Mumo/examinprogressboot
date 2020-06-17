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
package com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.add;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.MultipleChoiceQuestion;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.MultipleChoiceQuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   15th June, 2020
 */
@Service
public class AddMultipleChoiceQuestionServiceImpl implements AddMultipleChoiceQuestionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Transactional
    @Override
    public SaveResponse save( AddMultipleChoiceQuestionRequest request ) {

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setQuestionText( request.getQuestionText() );
        multipleChoiceQuestion.setScore( request.getScore() );
        multipleChoiceQuestion.setSection( section );
        multipleChoiceQuestionRepository.save( multipleChoiceQuestion );

        for ( AddMultipleChoiceQuestionAnswerRequest answerRequest : request
                .getAddMultipleChoiceQuestionAnswerRequests() ) {
            Answer answer = new Answer();
            answer.setAnswerText( answerRequest.getAnswerText() );
            answer.setCorrect( answerRequest.isCorrect() );
            answer.setMultipleChoiceQuestion( multipleChoiceQuestion );
            answerRepository.save( answer );
        }

        return new SaveResponse( true, null );
    }

}
