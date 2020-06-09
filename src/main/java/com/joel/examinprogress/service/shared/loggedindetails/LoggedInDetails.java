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
public class LoggedInDetails {

    private Long userId;
    private String firstName;

    public LoggedInDetails() {

    }


    public LoggedInDetails( Long userId,
            String firstName ) {

        this.userId = userId;
        this.firstName = firstName;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId( Long userId ) {

        this.userId = userId;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName( String firstName ) {

        this.firstName = firstName;
    }
}
