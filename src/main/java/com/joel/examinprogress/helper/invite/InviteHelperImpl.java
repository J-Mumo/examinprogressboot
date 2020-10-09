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
package com.joel.examinprogress.helper.invite;

import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.helper.time.TimeHelper;

/**
 * @author Joel Mumo
 * @date   Oct 9, 2020
 */
@Service
public class InviteHelperImpl implements InviteHelper {

    @Autowired
    private TimeHelper timeHelper;

    @Override
    public Boolean inviteExpired( Invite invite, ExamStatus examStatus ) {

        Boolean expired = Boolean.FALSE;

        if ( invite.getExamEndDate() != null && invite.getExamEndDate().isBefore( LocalDate
                .now() ) ) {

            expired = Boolean.TRUE;
        }
        else if ( invite.getExamEndDate() == null ) {

            Exam exam = invite.getExam();
            Long totalExamTime = exam.getTotalExamTime().toSeconds();
            Calendar createdAt = examStatus.getCreatedAt();
            expired = timeHelper.hasExpired( createdAt, totalExamTime.intValue() );
        }

        return expired;
    }

}
