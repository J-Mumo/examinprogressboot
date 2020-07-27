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
public class SendInviteRequest {

    private Long inviteId;
    private String emails[];

    public SendInviteRequest( Long inviteId, String emails[] ) {

        super();
        this.inviteId = inviteId;
        this.emails = emails;
    }


    public Long getInviteId() {

        return inviteId;
    }


    public void setInviteId( Long inviteId ) {

        this.inviteId = inviteId;
    }


    public String[] getEmails() {

        return emails;
    }


    public void setEmails( String[] emails ) {

        this.emails = emails;
    }
}
