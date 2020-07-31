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
package com.joel.examinprogress.service.teacher.exam.invite.invites;

/**
 * @author Joel Mumo
 * @date   30th July, 2020
 */
public class InvitesInitialData {

    private InviteTransfer[] inviteTransfers;

    public InvitesInitialData( InviteTransfer[] inviteTransfers ) {

        super();
        this.inviteTransfers = inviteTransfers;
    }


    public InviteTransfer[] getInviteTransfers() {

        return inviteTransfers;
    }


    public void setInviteTransfers( InviteTransfer[] inviteTransfers ) {

        this.inviteTransfers = inviteTransfers;
    }
}
