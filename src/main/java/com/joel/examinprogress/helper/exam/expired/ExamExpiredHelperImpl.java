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
package com.joel.examinprogress.helper.exam.expired;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Invite;

/**
 * @author Joel Mumo
 * @date   30th Aug, 2020
 */
@Service
public class ExamExpiredHelperImpl implements ExamExpiredHelper {

    @Override
    public Boolean examHasExpired( Invite invite ) {

        Date examStartDate = invite.getExamStartDate();
        Date examEndDate = invite.getExamEndDate();
        LocalTime examStartTime = invite.getExamStartTime();
        Boolean pausable = invite.getPausable();
        return null;
    }

}
