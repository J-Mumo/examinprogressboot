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
package com.joel.examinprogress.repository.student;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByExamTokensIn( Set<ExamToken> examTokens );


    Student findByUser( User user );

}
