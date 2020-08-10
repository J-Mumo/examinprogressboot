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

    private String inviteCode;

    public SendInviteInitialData( String inviteCode ) {

        super();
        this.inviteCode = inviteCode;
    }


    public String getInviteCode() {

        return inviteCode;
    }


    public void setInviteCode( String inviteCode ) {

        this.inviteCode = inviteCode;
    }
}
