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
package com.joel.examinprogress.service.teacher.exam.invite.view;

import java.time.LocalDate;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
public class ViewInviteInitialData {

    private String name;
    private LocalDate examStartDate;
    private LocalDate examEndDate;
    private boolean pausable;
    private String examStartTime;
    private String inviteCode;
    private ExamTokenTransfer[] examTokenTransfers;

    public ViewInviteInitialData(
            String name,
            LocalDate examStartDate,
            LocalDate examEndDate,
            boolean pausable,
            String examStartTime,
            String inviteCode,
            ExamTokenTransfer[] examTokenTransfers ) {

        super();
        this.name = name;
        this.examStartDate = examStartDate;
        this.examEndDate = examEndDate;
        this.pausable = pausable;
        this.examStartTime = examStartTime;
        this.inviteCode = inviteCode;
        this.examTokenTransfers = examTokenTransfers;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public LocalDate getExamStartDate() {

        return examStartDate;
    }


    public void setExamStartDate( LocalDate examStartDate ) {

        this.examStartDate = examStartDate;
    }


    public LocalDate getExamEndDate() {

        return examEndDate;
    }


    public void setExamEndDate( LocalDate examEndDate ) {

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


    public String getInviteCode() {

        return inviteCode;
    }


    public void setInviteCode( String inviteCode ) {

        this.inviteCode = inviteCode;
    }


    public ExamTokenTransfer[] getExamTokenTransfers() {

        return examTokenTransfers;
    }


    public void setExamTokenTransfers( ExamTokenTransfer[] examTokenTransfers ) {

        this.examTokenTransfers = examTokenTransfers;
    }
}
