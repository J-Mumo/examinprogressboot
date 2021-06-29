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
package com.joel.examinprogress.service.teacher.results.examresults;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerTypeEnum;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.invite.InviteHelper;
import com.joel.examinprogress.helper.result.ResultHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.student.ExamStatusRepository;

/**
 * @author Joel Mumo
 * @date   Oct 8, 2020
 */
@Service
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ResultHelper resultHelper;

    @Autowired
    private InviteHelper inviteHelper;

    @Autowired
    private StudentExamResultComparator studentExamResultComparator;

    private StudentExamResult createStudentExamResult( Result result ) {

        Student student = result.getStudent();
        Exam exam = result.getExam();
        User studentUser = student.getUser();
        Long studentId = student.getId();
        String name = studentUser.getFirstName() + " " + studentUser.getLastName();
        Float percentScore = result.getPercentScore();
        Boolean examInProgress = Boolean.TRUE;
        Boolean viewPerformance = Boolean.FALSE;
        Boolean finalizeScoring = Boolean.FALSE;
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
        Set<Question> questions = new HashSet<>();
        Set<Section> sections = exam.getSections();
        Invite invite = exam.getInvite();
        Boolean inviteExpired = inviteHelper.inviteExpired( invite, examStatus );

        for ( Section section : sections ) {

            questions.addAll( section.getQuestions() );
        }

        if ( examStatus.getComplete() || inviteExpired ) {

            examInProgress = Boolean.FALSE;
        }

        if ( examStatus.getScoringComplete() ) {

            viewPerformance = Boolean.TRUE;
        }
        else {

            for ( Question question : questions ) {

                Result questionResult = resultRepository.findByQuestionAndStudent( question,
                        student );

                if ( questionResult != null && questionResult.getPointScore() == null ) {

                    if ( question.getAnswerType().getId() == AnswerTypeEnum.TEXT_ANSWER
                            .getAnswerTypeId() ) {

                        finalizeScoring = Boolean.TRUE;
                        break;
                    }
                    else {

                        /** Update results with zero score when question was not answered */
                        Integer studentTextAnswerScore = null;
                        resultHelper.updateResult( question, student, studentTextAnswerScore );
                    }
                }
            }

            if ( !finalizeScoring ) {

                examStatus.setScoringComplete( Boolean.TRUE );
                examStatusRepository.save( examStatus );
                viewPerformance = Boolean.TRUE;
            }
        }

        return new StudentExamResult( studentId, name, percentScore, examInProgress,
                viewPerformance, finalizeScoring );
    }

    private StudentExamResult[] createStudentExamResults( Set<Result> results ) {

        SortedSet<StudentExamResult> studentExamResults =
                new TreeSet<>( studentExamResultComparator );

        for ( Result result : results ) {

            studentExamResults.add( createStudentExamResult( result ) );
        }

        return studentExamResults.toArray( new StudentExamResult[studentExamResults.size()] );
    }


    @Transactional
    @Override
    public ResultsInitialData getResultsInitialData( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        Set<Result> results = exam.getResults();
        StudentExamResult[] studentExamResults = createStudentExamResults( results );
        return new ResultsInitialData( studentExamResults );
    }
}
