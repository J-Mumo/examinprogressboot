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
package com.joel.examinprogress.service.shared.loggedindetails;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class LoggedInUserDetails {

    private Long userId;
    private String username;

    public LoggedInUserDetails() {

    }


    public LoggedInUserDetails( Long userId,
            String username ) {

        this.userId = userId;
        this.username = username;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId( Long userId ) {

        this.userId = userId;
    }


    public String getUsername() {

        return username;
    }


    public void setUserName( String username ) {

        this.username = username;
    }
}
