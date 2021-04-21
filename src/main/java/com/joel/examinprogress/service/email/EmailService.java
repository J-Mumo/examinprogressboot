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

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
import java.util.Locale;

import com.joel.examinprogress.domain.contact.ContactQuery;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;

public interface EmailService {

    EmailActivationResponse activateUserEmail( String emailActivateCode );


    EmailSentResponse sendActivationEmail( DomainOrganisation oraganisation,
            User user,
            String email, Locale locale, String domain, int serverPort,
            String protocol );


    EmailSentResponse sendForgottenPasswordEmail( ForgottenPasswordRequest request, String domain,
            int serverPort, String protocol );


    EmailSentResponse sendInviteToExam( DomainOrganisation organisation, String email,
            String examToken, Invite invite, Locale locale, String domain, int serverPort,
            String protocol );


    EmailSentResponse notifyAdminsOnContactQuery( DomainOrganisation organisation,
            ContactQuery contactQuery, Locale locale, int serverPort, String protocol );
}
