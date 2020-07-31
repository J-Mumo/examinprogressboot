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
package com.joel.examinprogress.service.teacher.exam.invite.edit;

import java.util.Date;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
public class EditInviteRequest {

    private Long inviteId;
    private String name;
    private Date examStartDate;
    private Date examEndDate;
    private boolean pausable;
    private String examStartTime;

    public EditInviteRequest(
            Long inviteId,
            String name,
            Date examStartDate,
            Date examEndDate,
            boolean pausable,
            String examStartTime ) {

        super();
        this.inviteId = inviteId;
        this.name = name;
        this.examStartDate = examStartDate;
        this.examEndDate = examEndDate;
        this.pausable = pausable;
        this.examStartTime = examStartTime;
    }


    public Long getInviteId() {

        return inviteId;
    }


    public void setInviteId( Long inviteId ) {

        this.inviteId = inviteId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public Date getExamStartDate() {

        return examStartDate;
    }


    public void setExamStartDate( Date examStartDate ) {

        this.examStartDate = examStartDate;
    }


    public Date getExamEndDate() {

        return examEndDate;
    }


    public void setExamEndDate( Date examEndDate ) {

        this.examEndDate = examEndDate;
    }


    public boolean isPausable() {

        return pausable;
    }


    public void setPausable( boolean pausable ) {

        this.pausable = pausable;
    }


    public String getExamStartTime() {

        return examStartTime;
    }


    public void setExamStartTime( String examStartTime ) {

        this.examStartTime = examStartTime;
    }
}
