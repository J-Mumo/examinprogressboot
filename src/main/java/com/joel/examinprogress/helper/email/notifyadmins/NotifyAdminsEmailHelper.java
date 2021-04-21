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
package com.joel.examinprogress.helper.email.notifyadmins;

import java.util.Locale;

import com.joel.examinprogress.domain.contact.ContactQuery;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.helper.email.EmailSentResponse;

/**
 * @author Joel Mumo
 * @date   Apr 21, 2021
 */
public interface NotifyAdminsEmailHelper {

    EmailSentResponse notifyAdminsOnContactQuery( DomainOrganisation organisation,
            ContactQuery contactQuery, Locale locale, int serverPort, String protocol );
}
