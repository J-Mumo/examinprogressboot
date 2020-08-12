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

import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private ExamRepository examRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String getHashWithBcrypt( Long id, String email ) {

        String hashed = passwordEncoder.encode( id + email );
        return hashed;
    }


    @Transactional
    @Override
    public SaveResponseWithId save( CreateInviteRequest request ) {

        LocalTime examStartTime = null;
        if ( request.getExamStartTime() != null ) {

            examStartTime = LocalTime.parse( request.getExamStartTime() );
        }

        Exam exam = examRepository.findById( request.getExamId() ).get();
        Invite invite = new Invite();
        invite.setName( request.getName() );
        invite.setExamStartDate( request.getExamStartDate() );
        invite.setExamEndDate( request.getExamEndDate() );
        invite.setPausable( request.isPausable() );
        invite.setExamStartTime( examStartTime );
        String inviteCode = getHashWithBcrypt( invite.getId(), exam.getTeacher().getUser()
                .getEmail() ).replaceAll( "/", "sL4sh" );

        invite.setInviteCode( inviteCode );
        invite.setExam( exam );
        inviteRepository.save( invite );

        return new SaveResponseWithId( true, null, invite.getId() );
    }

}
