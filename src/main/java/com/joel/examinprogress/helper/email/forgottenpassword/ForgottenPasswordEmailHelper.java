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
