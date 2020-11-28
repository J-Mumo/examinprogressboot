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
package com.joel.examinprogress.service.teacher.token.payment;


/**
 * @author Joel Mumo
 * @date   Nov 27, 2020
 */
public class UpdateTokenResponse {

    private Boolean updated;
    private String error;
    private Integer tokens;

    public UpdateTokenResponse( Boolean updated, String error, Integer tokens ) {

        super();
        this.updated = updated;
        this.error = error;
        this.tokens = tokens;
    }


    /**
     * @return the updated
     */
    public Boolean getUpdated() {

        return updated;
    }


    /**
     * @param updated the updated to set
     */
    public void setUpdated( Boolean updated ) {

        this.updated = updated;
    }


    /**
     * @return the error
     */
    public String getError() {

        return error;
    }


    /**
     * @param error the error to set
     */
    public void setError( String error ) {

        this.error = error;
    }


    /**
     * @return the tokens
     */
    public Integer getTokens() {

        return tokens;
    }


    /**
     * @param tokens the tokens to set
     */
    public void setTokens( Integer tokens ) {

        this.tokens = tokens;
    }
}
