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
package com.joel.examinprogress.service.teacher.exam.invite.send;

/**
 * @author Joel Mumo
 * @date   25th July, 2020
 */
public class SendInviteInitialData {

    private String examLink;

    public SendInviteInitialData( String examLink ) {

        super();
        this.examLink = examLink;
    }


    public String getExamLink() {

        return examLink;
    }


    public void setExamLink( String examLink ) {

        this.examLink = examLink;
    }
}
