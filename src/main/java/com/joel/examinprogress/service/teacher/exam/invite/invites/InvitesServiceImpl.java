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

import java.time.LocalTime;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.InviteRepository;

/**
 * @author Joel Mumo
 * @date   30th July, 2020
 */
@Service
public class InvitesServiceImpl implements InvitesService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private InviteTransferComparator inviteTransferComparator;

    private InviteTransfer createInviteTransfer( Invite invite ) {

        LocalTime startTime = invite.getExamStartTime();
        String examStartTime = startTime != null ? startTime.toString() : null;

        InviteTransfer transfer = new InviteTransfer( invite.getId(), invite.getName(), invite
                .getExamStartDate(), invite.getExamEndDate(), invite.getPausable(), examStartTime );

        return transfer;
    }


    private InviteTransfer[] createInviteTransfers( Set<Invite> invites ) {

        SortedSet<InviteTransfer> inviteTransfers =
                new TreeSet<>( inviteTransferComparator );

        for ( Invite invite : invites ) {

            inviteTransfers.add( createInviteTransfer( invite ) );
        }

        return inviteTransfers.toArray( new InviteTransfer[inviteTransfers.size()] );
    }


    @Override
    public InvitesInitialData getInitialData( Long examId ) {

        Set<Invite> invites = inviteRepository.findByExamId( examId );
        InviteTransfer[] inviteTransfers = createInviteTransfers( invites );

        return new InvitesInitialData( inviteTransfers );
    }

}
