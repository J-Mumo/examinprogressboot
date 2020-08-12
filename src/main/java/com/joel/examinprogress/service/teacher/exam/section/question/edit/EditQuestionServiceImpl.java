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
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.QuestionTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerType;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerTypeEnum;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceQuestionAnswerRequest;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
@Service
public class EditQuestionServiceImpl implements EditQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

    @Autowired
    private AnswerRepository answerRepository;

    private MultipleChoiceAnswerTransfer createMultipleChoiceAnswerTransfer(
            Answer answer, Set<Answer> correctAnswers ) {

        boolean correct = false;
        for ( Answer correctAnswer : correctAnswers ) {
            if ( correctAnswer == answer )
                correct = true;
        }

        MultipleChoiceAnswerTransfer transfer = new MultipleChoiceAnswerTransfer(
                answer.getId(), answer.getAnswerText(), correct, answer.getAnswerType().getName() );

        return transfer;
    }


    private MultipleChoiceAnswerTransfer[] createMultipleChoiceAnswerTransfers( Question question,
            Set<Answer> answers ) {

        SortedSet<MultipleChoiceAnswerTransfer> multipleChoiceAnswerTransfers =
                new TreeSet<>( multipleChoiceAnswerTransferComparator );

        Set<Answer> correctAnswers = question.getAnswers();
        for ( Answer answer : answers ) {

            multipleChoiceAnswerTransfers.add( createMultipleChoiceAnswerTransfer(
                    answer, correctAnswers ) );
        }

        return multipleChoiceAnswerTransfers.toArray(
                new MultipleChoiceAnswerTransfer[multipleChoiceAnswerTransfers.size()] );
    }


    private void saveMultipleChoiceAnswers( Question question,
            MultipleChoiceQuestionAnswerRequest[] requests ) {

        Set<Answer> correctMultipleChoiceAnswers = new HashSet<Answer>();
        Set<Answer> multipleChoiceAnswers = answerRepository
                .findByQuestion( question );

        AnswerType answerType = multipleChoiceAnswers.iterator().next().getAnswerType();
        answerRepository.deleteAll( multipleChoiceAnswers );

        for ( MultipleChoiceQuestionAnswerRequest answerRequest : requests ) {

            Answer answer = new Answer();
            answer.setAnswerText( answerRequest.getAnswerText() );
            answer.setQuestion( question );
            answer.setAnswerType( answerType );
            answerRepository.save( answer );

            if ( answerRequest.isCorrect() ) {
                correctMultipleChoiceAnswers.add( answer );
            }
        }

        question.setAnswers( correctMultipleChoiceAnswers );
        questionRepository.save( question );
    }


    @Override
    public EditQuestionInitialData getInitialData( Long questionId ) {

        Exam exam = questionRepository.findById( questionId ).get().getSection().getExam();
        boolean examTimedByQuestion = false;

        if ( ExamTimerTypeEnum.TIMED_PER_QUESTION.getName().equals( exam.getExamTimerType()
                .getName() ) ) {
            examTimedByQuestion = Boolean.TRUE;
        }

        Question question = questionRepository.findById( questionId ).get();
        AnswerType answerType = question.getAnswerType();
        boolean comprehensionQuestion = false;
        boolean comprehensionSubQuestion = false;

        if ( question.getQuestionType().getId().equals( QuestionTypeEnum.COMPREHENSION_QUESTION
                .getQuestionTypeId() ) ) {
            comprehensionQuestion = true;
        }
        if ( question.getQuestion() != null ) {
            comprehensionSubQuestion = true;
        }

        Duration questionDuration = question.getDuration();
        String duration = questionDuration != null ? String.format( "%d:%02d:%02d", questionDuration
                .getSeconds() / 3600,
                ( questionDuration.getSeconds() % 3600 ) / 60, ( questionDuration.getSeconds()
                        % 60 ) )
                : null;

        Set<Answer> answers = answerRepository.findByQuestion( question );

        String answer_type = answerType.getName();

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers = !comprehensionQuestion
                ? createMultipleChoiceAnswerTransfers( question, answers )
                : null;

        EditQuestionInitialData initialData = new EditQuestionInitialData( comprehensionQuestion,
                comprehensionSubQuestion, examTimedByQuestion, question.getQuestionText(), duration,
                question.getScore(), answer_type, multipleChoiceAnswerTransfers );

        return initialData;
    }


    @Override
    public SaveResponseWithId saveQuestion( EditQuestionRequest request ) {

        String duration[] = request.getDuration() != null ? request.getDuration().split( ":" )
                : null;
        String hour = duration != null ? duration[0] : "";
        String minute = duration != null ? duration[1] : "";
        String second = duration != null ? duration[2] : "";
        String questionTime = "PT" + hour + "H" + minute + "M" + second + "S";
        Duration questionDuration = request.getDuration() != null ? Duration.parse( questionTime )
                : null;

        Question question = questionRepository.findById( request.getQuestionId() ).get();
        Exam exam = question.getSection().getExam();
        Long currentQuestionMinutes = question.getDuration() != null ? question.getDuration()
                .toMinutes() : 0;

        question.setQuestionText( request.getQuestionText() );
        question.setScore( request.getScore() );
        question.setDuration( questionDuration );
        questionRepository.save( question );

        if ( !request.getAnswerType().equals( AnswerTypeEnum.TEXT_ANSWER.getName() ) ) {

            saveMultipleChoiceAnswers( question, request
                    .getMultipleChoiceQuestionAnswerRequests() );
        }

        if ( exam.getExamTimerType().getId() == ExamTimerTypeEnum.TIMED_PER_SECTION
                .getExamTimerTypeId() && questionDuration != null ) {

            Long examTime = exam.getTotalExamTime().toMinutes() - currentQuestionMinutes
                    + questionDuration
                            .toMinutes();

            exam.setTotalExamTime( Duration.ofMinutes( examTime ) );
            examRepository.save( exam );
        }

        Long id = question.getId();
        if ( question.getQuestion() != null ) {
            id = question.getQuestion().getId();
        }

        return new SaveResponseWithId( true, null, id );
    }
}
