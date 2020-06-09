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
package com.joel.examinprogress.service.forgottenpassword.reset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.user.ForgottenPassword;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.hash.HashHelper;
import com.joel.examinprogress.repository.user.ForgottenPasswordRepository;
import com.joel.examinprogress.repository.user.UserRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
@Service
public class ResetUserForgottenPasswordServiceImpl implements
        ResetUserForgottenPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgottenPasswordRepository forgottenPasswordRepository;

    @Autowired
    private HashHelper hashHelper;

    @Override
    public SaveResponse resetUserForgottenPassword(
            UserResetForgottenPasswordRequest userResetForgottenPasswordRequest ) {

        SaveResponse saveUserResponse = null;

        User user = userRepository.findById( userResetForgottenPasswordRequest
                .getUserId() ).orElse( null );
        if ( user == null ) {
            saveUserResponse = new SaveResponse( Boolean.FALSE,
                    "User Id supplied doesnot exist" );
        }

        ForgottenPassword forgottenPassword = forgottenPasswordRepository
                .findByUserAndCode( user, userResetForgottenPasswordRequest
                        .getCode() );

        if ( forgottenPassword != null ) {

            if ( !forgottenPassword.getActive() ) {

                user.setPasswordHash( hashHelper.getPasswordHashWithBcrypt(
                        userResetForgottenPasswordRequest.getPassword() ) );

                userRepository.save( user );

                forgottenPassword.setActive( Boolean.TRUE );
                forgottenPasswordRepository.save( forgottenPassword );
                saveUserResponse = new SaveResponse( Boolean.TRUE, null );
            }
            else {
                saveUserResponse = new SaveResponse( Boolean.FALSE,
                        "The code has already been used. Please send "
                                + "another email to reset your password" );
            }

        }
        else {

            saveUserResponse = new SaveResponse( Boolean.FALSE,
                    "Invalid forgotten password reset link" );
        }

        return saveUserResponse;
    }


    @Override
    public VerificationResponse verifyForgottenPasswordResetCode(
            UserVerifyForgottenPasswordCodeRequest userVerifyForgottenPasswordCodeRequest ) {

        VerificationResponse verificationResponse = null;

        User user = userRepository.findById(
                userVerifyForgottenPasswordCodeRequest.getUserId() ).get();

        ForgottenPassword forgottenPassword = forgottenPasswordRepository
                .findByUserAndCode( user, userVerifyForgottenPasswordCodeRequest
                        .getCode() );

        if ( forgottenPassword != null ) {

            if ( forgottenPassword.getActive() ) {

                verificationResponse = new VerificationResponse( Boolean.TRUE,
                        userVerifyForgottenPasswordCodeRequest.getCode()
                                + " has already been used" );
            }
            else {

                verificationResponse = new VerificationResponse( Boolean.FALSE,
                        "Link is valid" );
            }

        }
        else {

            verificationResponse = new VerificationResponse( Boolean.FALSE,
                    null );
        }
        return verificationResponse;
    }

}
