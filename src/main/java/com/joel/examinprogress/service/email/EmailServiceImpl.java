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
package com.joel.examinprogress.service.email;

import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.contact.ContactQuery;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.ForgottenPassword;
import com.joel.examinprogress.domain.user.Role;
import com.joel.examinprogress.domain.user.RoleEnum;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.email.forgottenpassword.ForgottenPasswordEmailHelper;
import com.joel.examinprogress.helper.email.notifyadmins.NotifyAdminsEmailHelper;
import com.joel.examinprogress.helper.email.sendactivationlink.SendActivationLinkEmailHelper;
import com.joel.examinprogress.helper.email.sendexaminvite.SendExamInviteEmailHelper;
import com.joel.examinprogress.helper.hash.HashHelper;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.user.ForgottenPasswordRepository;
import com.joel.examinprogress.repository.user.RoleRepository;
import com.joel.examinprogress.repository.user.UserRepository;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgottenPasswordRepository forgottenPasswordRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    private HashHelper hashHelper;

    @Autowired
    private ForgottenPasswordEmailHelper forgottenPasswordEmailHelper;

    @Autowired
    private SendActivationLinkEmailHelper sendActivationLinkEmailHelper;

    @Autowired
    private SendExamInviteEmailHelper sendExamInviteEmailHelper;

    @Autowired
    private NotifyAdminsEmailHelper notifyAdminsEmailHelper;

    @Override
    public EmailSentResponse sendActivationEmail(
            DomainOrganisation organisation,
            User user, String email, Locale locale, String domain,
            int serverPort, String protocol ) {

        EmailSentResponse emailSentResponse = sendActivationLinkEmailHelper
                .sendEmailActivationLink( organisation, user, email, locale,
                        domain, serverPort, protocol );

        return emailSentResponse;
    }


    @Override
    @Transactional
    public EmailActivationResponse activateUserEmail(
            String emailActivationCode ) {

        User user = userRepository.findByEmailActivationCode(
                emailActivationCode );

        if ( user != null ) {

            user.setEmailActivationCode( null );

            Role role = roleRepository.findById( RoleEnum.EMAIL_VALIDATED
                    .getId() ).get();

            user.getRoles().add( role );
            user.setEnabled( Boolean.TRUE );
            userRepository.save( user );
            return new EmailActivationResponse( Boolean.TRUE, "" );
        }

        return new EmailActivationResponse( Boolean.FALSE,
                "Cannot find user with code" );
    }


    @Override
    public EmailSentResponse sendForgottenPasswordEmail(
            ForgottenPasswordRequest request, String domain, int serverPort,
            String protocol ) {

        Locale locale = Locale.forLanguageTag( "en" );

        DomainOrganisation organisation = organisationRepository.findByDomain(
                domain );

        String email = request.getEmail();
        String emailtolower = email.toLowerCase();
        EmailSentResponse emailSentResponse = new EmailSentResponse();

        User user = userRepository.findByEmail( emailtolower );

        if ( user != null ) {

            ForgottenPassword forgottenPassword = forgottenPasswordRepository
                    .findByUser( user );

            if ( forgottenPassword != null ) {
                forgottenPasswordRepository.delete( forgottenPassword );
            }

            forgottenPassword = new ForgottenPassword();
            forgottenPassword.setCode( hashHelper.getRandomHash() );
            forgottenPassword.setUser( user );
            forgottenPassword.setActive( Boolean.FALSE );
            forgottenPasswordRepository.save( forgottenPassword );

            emailSentResponse = forgottenPasswordEmailHelper
                    .sendEmailForgottenPassword( organisation, user, locale,
                            "/user/resetforgottenpassword", domain, serverPort,
                            protocol );
        }
        else {

            emailSentResponse = new EmailSentResponse( Boolean.FALSE,
                    "Unknown user email" );
        }
        return emailSentResponse;
    }


    @Override
    public EmailSentResponse sendInviteToExam( DomainOrganisation organisation,
            String email, String examToken, Invite invite, Locale locale, String domain,
            int serverPort, String protocol ) {

        EmailSentResponse emailSentResponse = sendExamInviteEmailHelper
                .sendExamInvite( organisation, email, examToken, invite, locale, domain, serverPort,
                        protocol );

        return emailSentResponse;
    }


    @Override
    public EmailSentResponse notifyAdminsOnContactQuery( DomainOrganisation organisation,
            ContactQuery contactQuery, Locale locale, int serverPort,
            String protocol ) {

        EmailSentResponse emailSentResponse = notifyAdminsEmailHelper.notifyAdminsOnContactQuery(
                organisation, contactQuery, locale, serverPort, protocol );

        return emailSentResponse;
    }
}
