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
public class EmailActivationRequest {

    private String email;
    private String localeStr;

    public EmailActivationRequest( String email, String localeStr ) {

        this.email = email;
        this.localeStr = localeStr;
    }


    public EmailActivationRequest() {

        super();
    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }


    public String getLocaleStr() {

        return localeStr;
    }


    public void setLocaleStr( String localeStr ) {

        this.localeStr = localeStr;
    }
}
