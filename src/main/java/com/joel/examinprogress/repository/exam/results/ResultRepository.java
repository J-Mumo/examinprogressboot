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
package com.joel.examinprogress.repository.exam.results;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.result.Result;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Result findBySectionAndStudent( Section section, Student student );


    Result findByExamAndStudent( Exam exam, Student student );


    Result findByQuestionAndStudent( Question question, Student student );


    List<Result> findByQuestion( Question question );


    Set<Result> findByStudentAndSectionExam( Student student, Exam exam );
}
