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
package com.joel.examinprogress.service.teacher.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.teacher.TeacherRepository;

/**
 * @author Joel Mumo
 * @date   Nov 18, 2020
 */
@Service
public class DashboardService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    public Integer getTokens() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        return teacher.getTokens();
    }
}
