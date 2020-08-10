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
package com.joel.examinprogress.service.student.exam.verifytoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.student.StudentRepository;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
@Service
public class VerifyExamTokenServiceImpl implements VerifyExamTokenService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public VerifyExamTokenResponse verifyToken( VerifyExamTokenRequest request ) {

        boolean verified = false;
        boolean registered = false;
        String email = null;
        if ( request.isInviteCode() ) {
            Invite invite = inviteRepository.findByInviteCode( request.getCode() );
            if ( invite != null ) {
                verified = true;
                registered = true;
            }
        }
        else {
            ExamToken examToken = examTokenRepository.findByToken( request.getCode() );
            if ( examToken != null ) {
                verified = true;
                email = examToken.getEmail();
            }
            if ( studentRepository.findByExamToken( examToken ) != null )
                registered = true;
        }

        return new VerifyExamTokenResponse( verified, registered, email );
    }
}
