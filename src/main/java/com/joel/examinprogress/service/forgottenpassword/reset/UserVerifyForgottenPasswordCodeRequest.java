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
public class UserVerifyForgottenPasswordCodeRequest {

    private long userId;
    private String code;

    public UserVerifyForgottenPasswordCodeRequest( long userId, String code ) {

        this.userId = userId;
        this.code = code;
    }


    public UserVerifyForgottenPasswordCodeRequest() {

        super();
    }


    public long getUserId() {

        return userId;
    }


    public void setUserId( long userId ) {

        this.userId = userId;
    }


    public String getCode() {

        return code;
    }


    public void setCode( String code ) {

        this.code = code;
    }
}
