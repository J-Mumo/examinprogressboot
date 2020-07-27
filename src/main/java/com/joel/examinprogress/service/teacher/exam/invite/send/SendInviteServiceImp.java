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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   25th July, 2020
 */
@Service
public class SendInviteServiceImp implements SendInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean emailExists( String email ) {

        ExamToken examToken = examTokenRepository.findByEmail( email );

        if ( examToken == null ) {

            return false;
        }

        return true;
    }


    private String getHashWithBcrypt( Long id, String email ) {

        String hashed = passwordEncoder.encode( id + email );
        return hashed;
    }


    @Override
    public SendInviteInitialData getInitialData( Long inviteId ) {

        Invite invite = inviteRepository.findById( inviteId ).get();
        String examLink = invite.getExam().getExamLink();

        return new SendInviteInitialData( examLink );
    }


    @Override
    public SaveResponse sendInviteToEmail( SendInviteToEmailRequest request ) {

        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        String email = request.getEmail();
        boolean emailExists = emailExists( email );
        SaveResponse saveResponse;

        if ( emailExists ) {

            saveResponse = new SaveResponse( false, EMAIL_ERROR_RBKEY );
        }
        else {

            ExamToken examToken = new ExamToken();
            examToken.setEmail( request.getEmail() );
            examToken.setToken( getHashWithBcrypt( invite.getId(), email ) );
            examToken.setInvite( invite );
            examTokenRepository.save( examToken );

            saveResponse = new SaveResponse( true, null );
        }
        return saveResponse;
    }


    @Override
    public SaveResponse sendInvite( SendInviteRequest request ) {

        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        for ( String email : request.getEmails() ) {
            boolean emailExists = emailExists( email );

            if ( !emailExists ) {

                ExamToken examToken = new ExamToken();
                examToken.setEmail( email );
                examToken.setToken( getHashWithBcrypt( invite.getId(), email ) );
                examToken.setInvite( invite );
                examTokenRepository.save( examToken );
            }
        }
        return new SaveResponse( true, null );
    }

}
