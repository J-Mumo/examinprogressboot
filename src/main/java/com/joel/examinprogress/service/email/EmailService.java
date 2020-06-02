package com.joel.examinprogress.service.email;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
import java.util.Locale;

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
}
