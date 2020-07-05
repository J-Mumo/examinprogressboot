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
package com.joel.examinprogress.service.teacher.exam.section.view;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.ComprehensionQuestion;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.ComprehensionQuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.MultipleChoiceAnswerRepository;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransferComparator;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
@Service
public class ViewSectionServiceImpl implements ViewSectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ComprehensionQuestionRepository comprehensionQuestionRepository;

    @Autowired
    private MultipleChoiceAnswerRepository answerRepository;

    @Autowired
    private QuestionTransferComparator multipleChoiceQuestionTransferComparator;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

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


    private QuestionTransfer createMultipleChoiceQuestionTransfer(
            Question multipleChoiceQuestion ) {

        Set<MultipleChoiceAnswer> answers = answerRepository.findByQuestion(
                multipleChoiceQuestion );

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers =
                createMultipleChoiceAnswerTransfers( answers );

        QuestionTransfer transfer = new QuestionTransfer(
                multipleChoiceQuestion.getId(),
                multipleChoiceQuestion.getQuestionText(),
                multipleChoiceQuestion.getScore(),
                multipleChoiceAnswerTransfers );

        return transfer;
    }


    private QuestionTransfer[] createMultipleChoiceQuestionTransfers( Set<
            Question> questions ) {

        SortedSet<QuestionTransfer> multipleChoiceQuestionTransfers =
                new TreeSet<>( multipleChoiceQuestionTransferComparator );

        for ( Question question : questions ) {

            multipleChoiceQuestionTransfers.add( createMultipleChoiceQuestionTransfer(
                    question ) );
        }

        return multipleChoiceQuestionTransfers.toArray(
                new QuestionTransfer[multipleChoiceQuestionTransfers.size()] );
    }


    @Override
    public ViewSectionInitialData getInitialData( Long sectionId ) {

        Section section = sectionRepository.findById( sectionId ).get();
        String name = section.getName();
        String description = section.getDescription();
        Set<Question> questions = questionRepository.findBySectionId( sectionId );
        Set<ComprehensionQuestion> comprehensionQuestions = comprehensionQuestionRepository
                .findBySectionId( sectionId );

        for ( ComprehensionQuestion comprehensionQuestion : comprehensionQuestions ) {
            Set<Question> questionsInComprehensionQuestion = questionRepository
                    .findByComprehensionQuestion( comprehensionQuestion );

            questions.removeAll( questionsInComprehensionQuestion );
        }

        QuestionTransfer[] multipleChoiceQuestionTransfers =
                createMultipleChoiceQuestionTransfers( questions );

        ViewSectionInitialData initialData = new ViewSectionInitialData( name, description,
                multipleChoiceQuestionTransfers );

        return initialData;
    }

}
