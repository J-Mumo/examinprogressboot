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
package com.joel.examinprogress.helper.email.forgottenpassword;

import java.util.Locale;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public interface ForgottenPasswordEmailHelper {

    EmailSentResponse sendEmailForgottenPassword( DomainOrganisation domainOrganisation,
            User user, Locale locale, String contextPath, String domain,
            int serverPort, String protocol );
}
