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

import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.MultipleChoiceAnswerRepository;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.ComprehensionQuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransferComparator;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
public class ViewQuestionServiceImpl implements ViewQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private QuestionTransferComparator questionTransferComparator;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

    private MultipleChoiceAnswerTransfer createMultipleChoiceAnswerTransfer(
            MultipleChoiceAnswer answer, Set<MultipleChoiceAnswer> correctAnswers ) {

        boolean correct = false;
        for ( MultipleChoiceAnswer correctAnswer : correctAnswers ) {
            if ( answer == correctAnswer ) {
                correct = true;
            }
        }
        MultipleChoiceAnswerTransfer transfer = new MultipleChoiceAnswerTransfer(
                answer.getId(), answer.getAnswerText(), correct );

        return transfer;
    }


    private MultipleChoiceAnswerTransfer[] createMultipleChoiceAnswerTransfers( Set<
            MultipleChoiceAnswer> answers, Set<MultipleChoiceAnswer> correctAnswers ) {

        SortedSet<MultipleChoiceAnswerTransfer> multipleChoiceAnswerTransfers =
                new TreeSet<>( multipleChoiceAnswerTransferComparator );

        for ( MultipleChoiceAnswer answer : answers ) {

            multipleChoiceAnswerTransfers.add( createMultipleChoiceAnswerTransfer(
                    answer, correctAnswers ) );
        }

        return multipleChoiceAnswerTransfers.toArray(
                new MultipleChoiceAnswerTransfer[multipleChoiceAnswerTransfers.size()] );
    }


    private QuestionTransfer createQuestionTransfer(
            Question question ) {

        Set<MultipleChoiceAnswer> correctAnswers = question.getMultipleChoiceAnswers();
        Set<MultipleChoiceAnswer> answers = multipleChoiceAnswerRepository.findByQuestion(
                question );

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers =
                createMultipleChoiceAnswerTransfers( answers, correctAnswers );

        QuestionTransfer transfer = new QuestionTransfer(
                question.getId(),
                question.getQuestionType().getName(),
                question.getQuestionText(),
                question.getScore(),
                multipleChoiceAnswerTransfers );

        return transfer;
    }


    private QuestionTransfer[] createQuestionTransfers( Question comprehensionQuestion ) {

        Set<Question> questions = comprehensionQuestion.getQuestions();
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
                        % 60 ) )
                : null;

        QuestionTransfer[] questionTransfers = createQuestionTransfers( question );
        ComprehensionQuestionTransfer transfer = new ComprehensionQuestionTransfer( questionTxt,
                duration, questionTransfers );

        return transfer;
    }


    @Override
    public ViewQuestionInitialData getInitialData( Long questionId ) {

        Question question = questionRepository.findById( questionId ).get();
        boolean comprehensionQuestion = false;
        ComprehensionQuestionTransfer comprehensionQuestionTransfer = null;
        QuestionTransfer questionTransfer = null;

        if ( question.getQuestions() != null ) {
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
