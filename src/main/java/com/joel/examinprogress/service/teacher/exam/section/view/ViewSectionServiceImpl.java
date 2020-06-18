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
import com.joel.examinprogress.domain.exam.section.question.MultipleChoiceQuestion;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.MultipleChoiceQuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared.MultipleChoiceAnswerTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared.MultipleChoiceAnswerTransferComparator;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared.MultipleChoiceQuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared.MultipleChoiceQuestionTransferComparator;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
@Service
public class ViewSectionServiceImpl implements ViewSectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private MultipleChoiceQuestionTransferComparator multipleChoiceQuestionTransferComparator;

    @Autowired
    private MultipleChoiceAnswerTransferComparator multipleChoiceAnswerTransferComparator;

    private MultipleChoiceAnswerTransfer createMultipleChoiceAnswerTransfer( Answer answer ) {

        MultipleChoiceAnswerTransfer transfer = new MultipleChoiceAnswerTransfer(
                answer.getId(), answer.getAnswerText() );

        return transfer;
    }


    private MultipleChoiceAnswerTransfer[] createMultipleChoiceAnswerTransfers( Set<
            Answer> answers ) {

        SortedSet<MultipleChoiceAnswerTransfer> multipleChoiceAnswerTransfers =
                new TreeSet<>( multipleChoiceAnswerTransferComparator );

        for ( Answer answer : answers ) {

            multipleChoiceAnswerTransfers.add( createMultipleChoiceAnswerTransfer(
                    answer ) );
        }

        return multipleChoiceAnswerTransfers.toArray(
                new MultipleChoiceAnswerTransfer[multipleChoiceAnswerTransfers.size()] );
    }


    private MultipleChoiceQuestionTransfer createMultipleChoiceQuestionTransfer(
            MultipleChoiceQuestion multipleChoiceQuestion ) {

        Set<Answer> answers = answerRepository.findByMultipleChoiceQuestion(
                multipleChoiceQuestion );

        MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers =
                createMultipleChoiceAnswerTransfers( answers );

        MultipleChoiceQuestionTransfer transfer = new MultipleChoiceQuestionTransfer(
                multipleChoiceQuestion.getId(),
                multipleChoiceQuestion.getQuestionText(),
                multipleChoiceQuestion.getScore(),
                multipleChoiceAnswerTransfers );

        return transfer;
    }


    private MultipleChoiceQuestionTransfer[] createMultipleChoiceQuestionTransfers( Set<
            MultipleChoiceQuestion> multipleChoiceQuestions ) {

        SortedSet<MultipleChoiceQuestionTransfer> multipleChoiceQuestionTransfers =
                new TreeSet<>( multipleChoiceQuestionTransferComparator );

        for ( MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions ) {

            multipleChoiceQuestionTransfers.add( createMultipleChoiceQuestionTransfer(
                    multipleChoiceQuestion ) );
        }

        return multipleChoiceQuestionTransfers.toArray(
                new MultipleChoiceQuestionTransfer[multipleChoiceQuestionTransfers.size()] );
    }


    @Override
    public ViewSectionInitialData getInitialData( Long sectionId ) {

        Section section = sectionRepository.findById( sectionId ).get();
        String name = section.getName();
        String description = section.getDescription();
        Set<MultipleChoiceQuestion> multipleChoiceQuestions = multipleChoiceQuestionRepository
                .findBySectionId( sectionId );

        MultipleChoiceQuestionTransfer[] multipleChoiceQuestionTransfers =
                createMultipleChoiceQuestionTransfers( multipleChoiceQuestions );

        ViewSectionInitialData initialData = new ViewSectionInitialData( name, description,
                multipleChoiceQuestionTransfers );

        return initialData;
    }

}
