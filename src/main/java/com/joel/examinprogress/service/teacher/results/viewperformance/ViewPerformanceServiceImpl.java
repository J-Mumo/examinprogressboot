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
package com.joel.examinprogress.service.teacher.results.viewperformance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.results.ResultRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
import com.joel.examinprogress.service.student.exam.examinprogress.ExamResult;
import com.joel.examinprogress.service.student.exam.examinprogress.SectionResult;

/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
@Service
public class ViewPerformanceServiceImpl implements ViewPerformanceService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResultRepository resultRepository;

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


    @Override
    public ViewPerformanceInitialData getInitialData( ViewPerformanceRequestInitialData request ) {

        Long studentId = request.getStudentId();
        Long examId = request.getExamId();
        Student student = studentRepository.findById(studentId).get();
        Exam exam = examRepository.findById( examId ).get();
        Result result = resultRepository.findByExamAndStudent( exam, student );
        User user = student.getUser();
        String studentName = user.getFirstName() + " " + user.getLastName();
        ExamResult examResult = createExamResult( exam, student, result );
        return new ViewPerformanceInitialData( studentName, examResult );
    }
}
