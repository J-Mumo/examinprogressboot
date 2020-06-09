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
public class UserResetForgottenPasswordRequest {

    private long userId;
    private String code;
    private String password;

    public UserResetForgottenPasswordRequest( long userId, String code,
            String password ) {

        this.userId = userId;
        this.code = code;
        this.password = password;
    }


    public UserResetForgottenPasswordRequest() {

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


    public String getPassword() {

        return password;
    }


    public void setPassword( String password ) {

        this.password = password;
    }
}
