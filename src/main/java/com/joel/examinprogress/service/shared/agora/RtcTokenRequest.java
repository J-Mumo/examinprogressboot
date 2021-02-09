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
 * @date   Feb 6, 2021
 */
public class RtcTokenRequest {

    private String channelName;
    private boolean student;
    private Long examTokenId;

    public RtcTokenRequest( String channelName, boolean student, Long examTokenId ) {

        super();
        this.channelName = channelName;
        this.student = student;
        this.examTokenId = examTokenId;
    }


    /**
     * @return the channelName
     */
    public String getChannelName() {

        return channelName;
    }


    /**
     * @param channelName the channelName to set
     */
    public void setChannelName( String channelName ) {

        this.channelName = channelName;
    }


    /**
     * @return the student
     */
    public boolean isStudent() {

        return student;
    }


    /**
     * @param student the student to set
     */
    public void setStudent( boolean student ) {

        this.student = student;
    }


    /**
     * @return the examTokenId
     */
    public Long getExamTokenId() {

        return examTokenId;
    }


    /**
     * @param examTokenId the examTokenId to set
     */
    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }

}
