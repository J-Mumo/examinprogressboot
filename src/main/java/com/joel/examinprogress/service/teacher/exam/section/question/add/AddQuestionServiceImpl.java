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

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerTypeEnum;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.QuestionType;
import com.joel.examinprogress.domain.exam.section.question.QuestionTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerType;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionTypeRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerTypeRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.MultipleChoiceAnswerRepository;
import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.AnswerTypeTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceQuestionAnswerRequest;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTypeTransfer;

/**
 * @author Joel Mumo
 * @date   15th June, 2020
 */
@Service
public class AddQuestionServiceImpl implements AddQuestionService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private AnswerTypeRepository answerTypeRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private MultipleChoiceAnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionTypeTransfer createQuestionTypeTransfer( QuestionType questionType ) {

        QuestionTypeTransfer transfer = new QuestionTypeTransfer(
                questionType.getId(),
                questionType.getName() );

        return transfer;
    }


    private QuestionTypeTransfer[] createQuestionTypeTransfers( List<
            QuestionType> questionTypes ) {

        Set<QuestionTypeTransfer> questionTypeTransfers = new HashSet<>();

        for ( QuestionType questionType : questionTypes ) {

            questionTypeTransfers.add( createQuestionTypeTransfer( questionType ) );
        }

        return questionTypeTransfers.toArray( new QuestionTypeTransfer[questionTypeTransfers
                .size()] );
    }


    private AnswerTypeTransfer createAnswerTypeTransfer( AnswerType answerType ) {

        AnswerTypeTransfer transfer = new AnswerTypeTransfer(
                answerType.getId(),
                answerType.getName() );

        return transfer;
    }


    private AnswerTypeTransfer[] createAnswerTypeTransfers( List<
            AnswerType> answerTypes ) {

        Set<AnswerTypeTransfer> answerTypeTransfers = new HashSet<>();

        for ( AnswerType answerType : answerTypes ) {

            answerTypeTransfers.add( createAnswerTypeTransfer( answerType ) );
        }

        return answerTypeTransfers.toArray( new AnswerTypeTransfer[answerTypeTransfers
                .size()] );
    }


    private void saveQuestionWithMultipleChoiceAnswers( Question question,
            MultipleChoiceQuestionAnswerRequest[] multipleChoiceQuestionAnswerRequests ) {

        Set<MultipleChoiceAnswer> multipleChoiceAnswers = new HashSet<MultipleChoiceAnswer>();

        for ( MultipleChoiceQuestionAnswerRequest answerRequest : multipleChoiceQuestionAnswerRequests ) {
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
    }


    @Override
    public AddQuestionInitialData getInitialData( Long sectionId ) {

        Exam exam = sectionRepository.findById( sectionId ).get().getExam();
        boolean examTimedByQuestion = false;

        if ( ExamTimerTypeEnum.TIMED_PER_QUESTION.getName().equals( exam.getExamTimerType()
                .getName() ) ) {
            examTimedByQuestion = Boolean.TRUE;
        }

        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        List<AnswerType> answerTypes = answerTypeRepository.findAll();
        QuestionTypeTransfer[] questionTypeTransfers = createQuestionTypeTransfers( questionTypes );
        AnswerTypeTransfer[] answerTypeTransfers = createAnswerTypeTransfers( answerTypes );
        AddQuestionInitialData initialData = new AddQuestionInitialData( examTimedByQuestion,
                questionTypeTransfers, answerTypeTransfers );

        return initialData;
    }


    @Transactional
    @Override
    public SaveResponse saveQuestion( AddQuestionRequest request ) {

        Duration duration = null;
        if ( request.getDuration() != null ) {
            duration = Duration.parse( request.getDuration() );
        }

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        Long questionTypeId = QuestionTypeEnum.QUESTION.getQuestionTypeId();
        QuestionType questionType = questionTypeRepository.findById( questionTypeId ).get();
        AnswerType answerType = answerTypeRepository.findById( request.getAnswerTypeId() ).get();
        Question question = new Question();
        question.setQuestionText( request.getQuestionText() );
        question.setScore( request.getScore() );
        question.setDuration( duration );
        question.setAnswerType( answerType );
        question.setSection( section );
        question.setQuestionType( questionType );
        questionRepository.save( question );

        if ( request.getMultipleChoiceQuestionAnswerRequests().length > 0 )
            saveQuestionWithMultipleChoiceAnswers( question, request
                    .getMultipleChoiceQuestionAnswerRequests() );

        return new SaveResponse( true, null );
    }


    @Transactional
    @Override
    public SaveResponseWithId saveComprehensionQuestion( AddComprehensionQuestionRequest request ) {

        Duration duration = null;
        if ( request.getDuration() != null ) {
            duration = Duration.parse( request.getDuration() );
        }

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        Long questionTypeId = QuestionTypeEnum.COMPREHENSION_QUESTION.getQuestionTypeId();
        QuestionType questionType = questionTypeRepository.findById( questionTypeId ).get();
        AnswerType answerType = answerTypeRepository.findById( request.getAnswerTypeId() ).get();
        Question comprehensionQuestion = new Question();
        if ( request.getComprehensionQuestionId() != null ) {
            comprehensionQuestion = questionRepository.findById( request
                    .getComprehensionQuestionId() ).get();
        }

        comprehensionQuestion.setQuestionText( request.getComprehension() );
        comprehensionQuestion.setQuestionType( questionType );
        comprehensionQuestion.setDuration( duration );
        comprehensionQuestion.setAnswerType( answerType );
        comprehensionQuestion.setSection( section );
        questionRepository.save( comprehensionQuestion );

        Long normalQuestionTypeId = QuestionTypeEnum.QUESTION.getQuestionTypeId();
        QuestionType normalQuestionType = questionTypeRepository.findById( normalQuestionTypeId )
                .get();
        Question question = new Question();
        question.setQuestionText( request.getQuestionRequest().getQuestionText() );
        question.setScore( request.getQuestionRequest().getScore() );
        question.setAnswerType( answerType );
        question.setQuestion( comprehensionQuestion );
        question.setSection( section );
        question.setQuestionType( normalQuestionType );
        questionRepository.save( question );

        if ( request.getQuestionRequest().getMultipleChoiceQuestionAnswerRequests().length > 0 )
            saveQuestionWithMultipleChoiceAnswers( question, request.getQuestionRequest()
                    .getMultipleChoiceQuestionAnswerRequests() );

        return new SaveResponseWithId( true, null, comprehensionQuestion.getId() );
    }

}