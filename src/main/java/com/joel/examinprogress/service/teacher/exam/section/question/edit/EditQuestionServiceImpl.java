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
package com.joel.examinprogress.service.teacher.exam.section.question.edit;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.MultipleChoiceAnswerRepository;
import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceQuestionAnswerRequest;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
public class EditQuestionServiceImpl implements EditQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

    @Autowired
    MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    private MultipleChoiceAnswerTransfer createMultipleChoiceAnswerTransfer(
            MultipleChoiceAnswer answer ) {

        MultipleChoiceAnswerTransfer transfer = new MultipleChoiceAnswerTransfer(
                answer.getId(), answer.getAnswerText() );

        return transfer;
    }


    private MultipleChoiceAnswerTransfer[] createMultipleChoiceAnswerTransfers( Set<
            MultipleChoiceAnswer> answers ) {

        SortedSet<MultipleChoiceAnswerTransfer> multipleChoiceAnswerTransfers =
                new TreeSet<>( multipleChoiceAnswerTransferComparator );

        for ( MultipleChoiceAnswer answer : answers ) {

            multipleChoiceAnswerTransfers.add( createMultipleChoiceAnswerTransfer(
                    answer ) );
        }

        return multipleChoiceAnswerTransfers.toArray(
                new MultipleChoiceAnswerTransfer[multipleChoiceAnswerTransfers.size()] );
    }


    private void saveMultipleChoiceAnswers( Question question,
            MultipleChoiceQuestionAnswerRequest[] requests ) {

        Set<MultipleChoiceAnswer> multipleChoiceAnswers = new HashSet<MultipleChoiceAnswer>();

        for ( MultipleChoiceQuestionAnswerRequest answerRequest : requests ) {
            MultipleChoiceAnswer answer = multipleChoiceAnswerRepository.findById( answerRequest
                    .getAnswerId() ).get();

            answer.setAnswerText( answerRequest.getAnswerText() );
            multipleChoiceAnswerRepository.save( answer );

            if ( answerRequest.isCorrect() ) {
                multipleChoiceAnswers.add( answer );
            }
            else
                multipleChoiceAnswers.remove( answer );
        }

        question.setMultipleChoiceAnswers( multipleChoiceAnswers );
        questionRepository.save( question );
    }


    @Override
    public EditQuestionInitialData getInitialData( Long questionId ) {

        Question question = questionRepository.findById( questionId ).get();
        boolean comprehensionQuestion = false;

        if ( question.getQuestions() != null ) {
            comprehensionQuestion = true;
        }

        Duration questionDuration = question.getDuration();
        String duration = questionDuration != null ? String.format( "%d:%02d:%02d", questionDuration
                .getSeconds() / 3600,
                ( questionDuration.getSeconds() % 3600 ) / 60, ( questionDuration.getSeconds()
                        % 60 ) )
                : null;

        Set<MultipleChoiceAnswer> answers = multipleChoiceAnswerRepository.findByQuestion(
                question );

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers =
                createMultipleChoiceAnswerTransfers( answers );

        EditQuestionInitialData initialData = new EditQuestionInitialData( comprehensionQuestion,
                question.getQuestionText(), duration, question.getScore(),
                multipleChoiceAnswerTransfers );

        return initialData;
    }


    @Override
    public SaveResponse saveQuestion( EditQuestionRequest request ) {

        String duration[] = request.getDuration() != null ? request.getDuration().split( ":" )
                : null;
        String hour = duration != null ? duration[0] : "";
        String minute = duration != null ? duration[1] : "";
        String second = duration != null ? duration[2] : "";
        String questionTime = "PT" + hour + "H" + minute + "M" + second + "S";
        Duration questionDuration = request.getDuration() != null ? Duration.parse( questionTime )
                : null;

        Question question = questionRepository.findById( request.getQuestionId() ).get();
        question.setQuestionText( request.getQuestionText() );
        question.setScore( request.getScore() );
        question.setDuration( questionDuration );
        questionRepository.save( question );

        if ( request.getMultipleChoiceQuestionAnswerRequests().length < 1 ) {

            saveMultipleChoiceAnswers( question, request
                    .getMultipleChoiceQuestionAnswerRequests() );
        }

        return new SaveResponse( true, null );
    }
}
