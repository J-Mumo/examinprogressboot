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
package com.joel.examinprogress.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUser( User user );


    Teacher findByUserId( Long userId );
}
