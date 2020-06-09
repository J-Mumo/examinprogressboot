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
public class EmailActivationResponse {

    private boolean emailActivation;
    private String error;

    public EmailActivationResponse( boolean emailActivation, String error ) {

        super();
        this.emailActivation = emailActivation;
        this.error = error;
    }


    public EmailActivationResponse() {

        super();
    }


    public boolean isEmailActivation() {

        return emailActivation;
    }


    public void setEmailActivation( boolean emailActivation ) {

        this.emailActivation = emailActivation;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }
}
