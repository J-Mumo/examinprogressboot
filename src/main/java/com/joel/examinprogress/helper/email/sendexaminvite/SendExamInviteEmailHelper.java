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
package com.joel.examinprogress.helper.email.sendexaminvite;

import java.util.Locale;

import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.helper.email.EmailSentResponse;

/**
 * @author Joel Mumo
 * @date   31st July, 2020
 */
public interface SendExamInviteEmailHelper {

    String SEND_EXAM_TOKEN = "SEND_EXAM_TOKEN";

    EmailSentResponse sendExamInvite( DomainOrganisation organisation, String email,
            String examToken, Invite invite, Locale locale, String domain, int serverPort,
            String protocol );
}
