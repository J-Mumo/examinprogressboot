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
package com.joel.examinprogress.service.student.exam.verifytoken;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
public class VerifyExamTokenResponse {

    private boolean verified;
    private boolean registered;
    private String email;

    public VerifyExamTokenResponse( boolean verified, boolean registered, String email ) {

        super();
        this.verified = verified;
        this.registered = registered;
        this.email = email;
    }


    public boolean isVerified() {

        return verified;
    }


    public void setVerified( boolean verified ) {

        this.verified = verified;
    }


    public boolean isRegistered() {

        return registered;
    }


    public void setRegistered( boolean registered ) {

        this.registered = registered;
    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }
}
