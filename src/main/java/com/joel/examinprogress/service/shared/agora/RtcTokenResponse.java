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
package com.joel.examinprogress.service.shared.agora;


/**
 * @author Joel Mumo
 * @date   Dec 17, 2020
 */
public class RtcTokenResponse {

    private String token;
    private int uid;

    public RtcTokenResponse( String token, int uid ) {

        super();
        this.token = token;
        this.uid = uid;
    }


    /**
     * @return the uid
     */
    public int getUid() {

        return uid;
    }


    /**
     * @param uid the uid to set
     */
    public void setUid( int uid ) {

        this.uid = uid;
    }


    /**
     * @return the token
     */
    public String getToken() {

        return token;
    }


    /**
     * @param token the token to set
     */
    public void setToken( String token ) {

        this.token = token;
    }
}
