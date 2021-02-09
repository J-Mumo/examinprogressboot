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
package com.joel.examinprogress.service.student.exam.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.QuestionTypeEnum;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerTypeEnum;
import com.joel.examinprogress.domain.student.QuestionStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.helper.result.ResultHelper;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.answer.AnswerRepository;
import com.joel.examinprogress.repository.student.QuestionStatusRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
import com.joel.examinprogress.service.student.exam.examinprogress.ExamResult;
import com.joel.examinprogress.service.student.exam.examinprogress.SectionResult;
import com.joel.examinprogress.service.teacher.results.sectionperformance.AnswerResult;
import com.joel.examinprogress.service.teacher.results.sectionperformance.QuestionResult;
import com.joel.examinprogress.service.teacher.results.sectionperformance.QuestionResultComparator;
import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceInitialData;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceInitialData;

/**
 * @author Joel Mumo
 * @date   Feb 9, 2021
 */
@Service
public class StudentResultServiceImpl implements StudentResultService {

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResultHelper resultHelper;

    @Autowired
    private QuestionResultComparator questionResultComparator;

    private SectionResult createSectionResult( Result result ) {

        Long sectionId = result.getSection().getId();
        String sectionName = result.getSection().getName();
        Float percentScore = result.getPercentScore();
        Integer pointsEarned = result.getPointScore();
        Integer sectionTotalPoints = result.getTotalScore();

        return new SectionResult( sectionId, sectionName, percentScore, pointsEarned,
                sectionTotalPoints );
    }


    private SectionResult[] createSectionResults( Student student, Exam exam ) {

        Set<Result> results = resultRepository.findByStudentAndSectionExam( student, exam );
        List<SectionResult> sectionResults = new ArrayList<>();

        for ( Result result : results ) {

            sectionResults.add( createSectionResult( result ) );
        }

        return sectionResults.toArray( new SectionResult[sectionResults.size()] );
    }


    private ExamResult createExamResult( Exam exam, Student student, Result result ) {

        boolean completeResult = true;
        String examName = exam.getName();
        Float percentScore = result.getPercentScore();
        Integer pointsEarned = result.getPointScore();
        Integer examTotalPoints = result.getTotalScore();
        SectionResult[] sectionResults = createSectionResults( student, exam );

        ExamResult examResult = new ExamResult( completeResult, examName, percentScore,
                pointsEarned, examTotalPoints, sectionResults );

        return examResult;
    }


    private AnswerResult createAnswerResult( Student student, Question question, Answer answer ) {

        String answerText = answer.getAnswerText();
        boolean selected = false;
        Set<Student> students = answer.getStudents();

        if ( students.contains( student ) )
            selected = true;

        return new AnswerResult( answerText, selected );
    }


    private AnswerResult[] createAnswerResults( Student student, Question question,
            boolean textAnswer ) {

        List<AnswerResult> answerResults = new ArrayList<>();

        if ( !textAnswer ) {

            Set<Answer> answers = answerRepository.findByQuestion( question );

            for ( Answer answer : answers ) {

                answerResults.add( createAnswerResult( student, question, answer ) );
            }
        }
        else {

            QuestionStatus questionStatus = questionStatusRepository.findByQuestionAndStudent(
                    question, student );

            String answer = questionStatus.getTextAnswer();
            boolean selected = false;
            AnswerResult answerResult = new AnswerResult( answer, selected );
            answerResults.add( answerResult );
        }

        return answerResults.toArray( new AnswerResult[answerResults.size()] );
    }


    private QuestionResult[] createSubQuestionResults( Student student, Set<Question> questions ) {

        SortedSet<QuestionResult> questionResults = new TreeSet<>( questionResultComparator );

        for ( Question question : questions ) {

            questionResults.add( createQuestionResult( student, question ) );

        }

        return questionResults.toArray( new QuestionResult[questionResults.size()] );
    }


    private QuestionResult createQuestionResult( Student student, Question question ) {

        Long comprehensionQuestionId = QuestionTypeEnum.COMPREHENSION_QUESTION.getQuestionTypeId();
        Long questionId = question.getId();
        String questionText = question.getQuestionText();
        boolean comprehensionQuestion = false;
        boolean textAnswer = false;
        boolean multipleAnswers = false;
        boolean singleAnswer = false;
        Long textAnswerId = AnswerTypeEnum.TEXT_ANSWER.getAnswerTypeId();
        Long singleAnswerId = AnswerTypeEnum.MULTIPLE_CHOICE_SINGLE_ANSWER.getAnswerTypeId();
        AnswerResult[] answerResults = null;
        QuestionResult[] questionResults = null;
        Result result = resultRepository.findByQuestionAndStudent( question, student );
        Integer pointsEarned = 0;
        Integer questionTotalPoints = 0;

        if ( comprehensionQuestionId == question.getQuestionType().getId() ) {

            comprehensionQuestion = true;
        }
        else if ( textAnswerId == question.getAnswerType().getId() ) {

            textAnswer = true;
        }
        else if ( singleAnswerId == question.getAnswerType().getId() ) {

            singleAnswer = true;
        }
        else
            multipleAnswers = true;

        if ( comprehensionQuestion ) {

            questionResults = createSubQuestionResults( student, question.getQuestions() );
        }
        else {

            if ( result == null ) {

                resultHelper.updateResult( question, student, 0 );
                result = resultRepository.findByQuestionAndStudent( question, student );
            }

            pointsEarned = result.getPointScore();
            questionTotalPoints = result.getTotalScore();
            answerResults = createAnswerResults( student, question, textAnswer );
        }

        QuestionResult questionResult = new QuestionResult( questionId, questionText,
                comprehensionQuestion, textAnswer, multipleAnswers, singleAnswer, pointsEarned,
                questionTotalPoints, answerResults, questionResults );

        return questionResult;
    }


    private QuestionResult[] createQuestionResults( Student student, Set<Question> questions ) {

        SortedSet<QuestionResult> questionResults = new TreeSet<>( questionResultComparator );

        for ( Question question : questions ) {

            if ( question.getQuestion() == null )
                questionResults.add( createQuestionResult( student, question ) );

        }

        return questionResults.toArray( new QuestionResult[questionResults.size()] );
    }


    @Override
    public ViewPerformanceInitialData getInitialData( Long examTokenId ) {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Student student = studentRepository.findByUser( user );
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        Exam exam = examToken.getInvite().getExam();
        Result result = resultRepository.findByExamAndStudent( exam, student );
        String studentName = user.getFirstName() + " " + user.getLastName();
        ExamResult examResult = createExamResult( exam, student, result );
        return new ViewPerformanceInitialData( studentName, examResult );
    }


    @Override
    public SectionPerformanceInitialData getSectionResult( Long sectionId ) {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Student student = studentRepository.findByUser( user );
        String studentName = user.getFirstName() + " " + user.getLastName();
        Section section = sectionRepository.findById( sectionId ).get();
        String sectionName = section.getName();
        Set<Question> questions = section.getQuestions();
        QuestionResult[] questionResults = createQuestionResults( student, questions );
        Result result = resultRepository.findBySectionAndStudent( section, student );
        Float percentScore = result.getPercentScore();
        Integer pointsEarned = result.getPointScore();
        Integer sectionTotalPoints = result.getTotalScore();

        return new SectionPerformanceInitialData( studentName, sectionName, percentScore,
                pointsEarned, sectionTotalPoints, questionResults );
    }

}
