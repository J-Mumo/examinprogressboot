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
package com.joel.examinprogress.service.teacher.exam.invite.edit;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
@Service
public class EditInviteServiceImpl implements EditInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public EditInviteInitialData getInitialData( Long inviteId ) {

        Invite invite = inviteRepository.findById( inviteId ).get();
        Duration startTime = invite.getExamStartTime();
        String examStartTime = startTime != null ? String.format( "%d:%02d:%02d", startTime
                .getSeconds() / 3600,
                ( startTime.getSeconds() % 3600 ) / 60, ( startTime.getSeconds() % 60 ) )
                : null;

        EditInviteInitialData initialData = new EditInviteInitialData( invite.getName(),
                invite.getExamStartDate(), invite.getExamEndDate(), invite.getPausable(),
                examStartTime );

        return initialData;
    }


    @Override
    public SaveResponse save( EditInviteRequest request ) {

        String startTime[] = request.getExamStartTime() != null ? request.getExamStartTime().split(
                ":" )
                : null;

        String hour = startTime != null ? startTime[0] : "";
        String minute = startTime != null ? startTime[1] : "";
        String time = "PT" + hour + "H" + minute + "M";
        Duration examStartTime = request.getExamStartTime() != null ? Duration.parse( time ) : null;

        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        invite.setName( request.getName() );
        invite.setExamStartDate( request.getExamStartDate() );
        invite.setExamEndDate( request.getExamEndDate() );
        invite.setPausable( request.isPausable() );
        invite.setExamStartTime( examStartTime );
        inviteRepository.save( invite );

        return new SaveResponse( true, null );
    }

}
