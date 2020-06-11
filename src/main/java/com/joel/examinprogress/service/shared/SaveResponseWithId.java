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
public class SaveResponseWithId {

    private boolean saved;
    private String error;
    private Long id;

    public SaveResponseWithId() {

    }


    public SaveResponseWithId( boolean saved, String error, Long id ) {

        this.saved = saved;
        this.error = error;
        this.id = id;
    }


    public boolean isSaved() {

        return saved;
    }


    public void setSaved( boolean saved ) {

        this.saved = saved;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }


    public Long getId() {

        return id;
    }


    public void setId( Long id ) {

        this.id = id;
    }

}
