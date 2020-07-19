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
package com.joel.examinprogress.service.teacher.exam.section.question.view;

import java.time.Duration;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.QuestionType;
import com.joel.examinprogress.domain.exam.section.question.QuestionTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.ComprehensionQuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransferComparator;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
@Service
public class ViewQuestionServiceImpl implements ViewQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionTransferComparator questionTransferComparator;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

    private MultipleChoiceAnswerTransfer createMultipleChoiceAnswerTransfer(
            Answer answer, Set<Answer> correctAnswers ) {

        boolean correct = false;
        for ( Answer correctAnswer : correctAnswers ) {
            if ( answer == correctAnswer ) {
                correct = true;
            }
        }
        MultipleChoiceAnswerTransfer transfer = new MultipleChoiceAnswerTransfer(
                answer.getId(), answer.getAnswerText(), correct, answer.getAnswerType().getName() );

        return transfer;
    }


    private MultipleChoiceAnswerTransfer[] createMultipleChoiceAnswerTransfers( Set<
            Answer> answers, Set<Answer> correctAnswers ) {

        SortedSet<MultipleChoiceAnswerTransfer> multipleChoiceAnswerTransfers =
                new TreeSet<>( multipleChoiceAnswerTransferComparator );

        for ( Answer answer : answers ) {

            multipleChoiceAnswerTransfers.add( createMultipleChoiceAnswerTransfer(
                    answer, correctAnswers ) );
        }

        return multipleChoiceAnswerTransfers.toArray(
                new MultipleChoiceAnswerTransfer[multipleChoiceAnswerTransfers.size()] );
    }


    private QuestionTransfer createQuestionTransfer(
            Question question ) {

        Set<Answer> correctAnswers = question.getAnswers();
        Set<Answer> answers = answerRepository.findByQuestion( question );

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers =
                createMultipleChoiceAnswerTransfers( answers, correctAnswers );

        Duration questionDuration = question.getDuration();
        String duration = questionDuration != null ? String.format( "%d:%02d:%02d", questionDuration
                .getSeconds() / 3600,
                ( questionDuration.getSeconds() % 3600 ) / 60, ( questionDuration.getSeconds()
                        % 60 ) ) : null;

        QuestionTransfer transfer = new QuestionTransfer(
                question.getId(),
                question.getQuestionType().getName(),
                question.getQuestionText(),
                question.getScore(), duration,
                multipleChoiceAnswerTransfers );

        return transfer;
    }


    private QuestionTransfer[] createQuestionTransfers( Question comprehensionQuestion ) {

        Set<Question> questions = questionRepository.findByQuestionId( comprehensionQuestion
                .getId() );
        SortedSet<QuestionTransfer> questionTransfers =
                new TreeSet<>( questionTransferComparator );

        for ( Question question : questions ) {

            questionTransfers.add( createQuestionTransfer(
                    question ) );
        }

        return questionTransfers.toArray(
                new QuestionTransfer[questionTransfers.size()] );
    }


    private ComprehensionQuestionTransfer createComprehensionQuestionTransfer( Question question ) {

        String questionTxt = question.getQuestionText();
        Duration questionDuration = question.getDuration();
        String duration = questionDuration != null ? String.format( "%d:%02d:%02d", questionDuration
                .getSeconds() / 3600,
                ( questionDuration.getSeconds() % 3600 ) / 60, ( questionDuration.getSeconds()
                        % 60 ) ) : null;

        QuestionTransfer[] questionTransfers = createQuestionTransfers( question );
        ComprehensionQuestionTransfer transfer = new ComprehensionQuestionTransfer( questionTxt,
                duration, questionTransfers );

        return transfer;
    }


    @Override
    public ViewQuestionInitialData getInitialData( Long questionId ) {

        Question question = questionRepository.findById( questionId ).get();
        QuestionType questionType = question.getQuestionType();
        boolean comprehensionQuestion = false;
        ComprehensionQuestionTransfer comprehensionQuestionTransfer = null;
        QuestionTransfer questionTransfer = null;

        if ( questionType.getId() == QuestionTypeEnum.COMPREHENSION_QUESTION.getQuestionTypeId() ) {
            comprehensionQuestion = true;
            comprehensionQuestionTransfer = createComprehensionQuestionTransfer( question );
        }
        else
            questionTransfer = createQuestionTransfer( question );

        ViewQuestionInitialData initialData = new ViewQuestionInitialData( comprehensionQuestion,
                questionTransfer, comprehensionQuestionTransfer );

        return initialData;
    }

}
