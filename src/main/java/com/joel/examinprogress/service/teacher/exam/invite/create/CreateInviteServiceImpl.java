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
package com.joel.examinprogress.service.teacher.exam.invite.create;

import java.time.Duration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;

/**
 * @author Joel Mumo
 * @date   25th July, 2020
 */
@Service
public class CreateInviteServiceImpl implements CreateInviteService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    InviteRepository inviteRepository;

    @Transactional
    @Override
    public SaveResponseWithId save( CreateInviteRequest request ) {

        Duration examStartTime = null;
        if ( request.getExamStartTime() != null ) {
            examStartTime = Duration.parse( request.getExamStartTime() );
        }

        Exam exam = examRepository.findById( request.getExamId() ).get();
        Invite invite = new Invite();
        invite.setName( request.getName() );
        invite.setExamStartDate( request.getExamStartDate() );
        invite.setExamEndDate( request.getExamEndDate() );
        invite.setPausable( request.isPausable() );
        invite.setExamStartTime( examStartTime );
        invite.setExam( exam );
        inviteRepository.save( invite );

        return new SaveResponseWithId( true, null, invite.getId() );
    }

}
