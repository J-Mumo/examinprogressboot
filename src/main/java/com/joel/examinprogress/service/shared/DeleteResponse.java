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
package com.joel.examinprogress.service.shared;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class DeleteResponse {

    private boolean deleted;
    private String error;

    public DeleteResponse() {

    }


    public DeleteResponse( boolean deleted, String error ) {

        this.deleted = deleted;
        this.error = error;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }


    public boolean isDeleted() {

        return deleted;
    }


    public void setDeleted( boolean deleted ) {

        this.deleted = deleted;
    }
}
