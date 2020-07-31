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

import java.util.Comparator;

import org.springframework.stereotype.Component;

/**
 * @author Joel Mumo
 * @date   30th July, 2020
 */
@Component
public class InviteTransferComparator implements Comparator<InviteTransfer> {

    @Override
    public int compare( InviteTransfer o1, InviteTransfer o2 ) {

        return -1 * o1.getInviteId().compareTo( o2.getInviteId() );
    }
}
