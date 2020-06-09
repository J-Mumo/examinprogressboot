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
package com.joel.examinprogress.service.forgottenpassword.reset;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public class VerificationResponse {

    private boolean status;
    private String response;

    public VerificationResponse( boolean status, String response ) {

        this.status = status;
        this.response = response;
    }


    public VerificationResponse() {

        super();
    }


    public boolean isStatus() {

        return status;
    }


    public void setStatus( boolean status ) {

        this.status = status;
    }


    public String getResponse() {

        return response;
    }


    public void setResponse( String response ) {

        this.response = response;
    }
}
