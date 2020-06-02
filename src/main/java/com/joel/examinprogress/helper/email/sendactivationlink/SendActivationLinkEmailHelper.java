package com.joel.examinprogress.helper.email.sendactivationlink;

import java.util.Locale;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface SendActivationLinkEmailHelper {

    String SEND_ACTIVATION_LINK = "SEND_ACTIVATION_LINK";

    EmailSentResponse sendEmailActivationLink( DomainOrganisation organisation,
            User user, String emailTo, Locale locale, String domain,
            int serverPort, String protocol );
}
