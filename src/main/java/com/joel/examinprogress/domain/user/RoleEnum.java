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
package com.joel.examinprogress.domain.user;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public enum RoleEnum {

    ADMIN( 1l ),
    EMAIL_VALIDATED( 2l ),
    DEV( 4l );

    private Long id;

    private RoleEnum( Long id ) {

        this.id = id;
    }


    public Long getId() {

        return id;
    }
}
