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
package com.joel.examinprogress.service.teacher.exam.invite.send;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Mumo
 * @date   May 31, 2021
 */
public class SendInviteResponse {

    private boolean sent;
    private boolean emailError;
    private boolean tokensError;
    private String error;
    private List<String> unsentEmails = new ArrayList<String>();

    public SendInviteResponse() {

    }

    public SendInviteResponse( boolean sent, boolean emailError, boolean tokensError,
            String error ) {

        this.sent = sent;
        this.emailError = emailError;
        this.tokensError = tokensError;
        this.error = error;
    }


    /**
     * @return the unsentEmails
     */
    public List<String> getUnsentEmails() {

        return unsentEmails;
    }


    /**
     * @param unsentEmails the unsentEmails to set
     */
    public void setUnsentEmails( List<String> unsentEmails ) {

        this.unsentEmails = unsentEmails;
    }


    /**
     * @return the sent
     */
    public boolean isSent() {

        return sent;
    }


    /**
     * @param sent the sent to set
     */
    public void setSent( boolean sent ) {

        this.sent = sent;
    }


    /**
     * @return the emailError
     */
    public boolean isEmailError() {

        return emailError;
    }


    /**
     * @param emailError the emailError to set
     */
    public void setEmailError( boolean emailError ) {

        this.emailError = emailError;
    }


    /**
     * @return the tokensError
     */
    public boolean isTokensError() {

        return tokensError;
    }


    /**
     * @param tokensError the tokensError to set
     */
    public void setTokensError( boolean tokensError ) {

        this.tokensError = tokensError;
    }


    /**
     * @return the error
     */
    public String getError() {

        return error;
    }


    /**
     * @param error the error to set
     */
    public void setError( String error ) {

        this.error = error;
    }
}
