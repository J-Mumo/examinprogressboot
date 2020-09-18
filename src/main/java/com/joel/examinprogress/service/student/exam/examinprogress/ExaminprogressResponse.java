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
package com.joel.examinprogress.service.student.exam.examinprogress;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
public class ExaminprogressResponse {

    private boolean examTokenNotFound;
    private boolean examNotFound;
    private boolean examHasStarted;
    private boolean examComplete;
    private boolean examExpired;
    private boolean timedPerExam;
    private boolean timedPerSection;
    private boolean timedPerQuestion;
    private boolean pausable;
    private boolean paused;
    private Long examTime;
    private LocalDate examStartDate;
    private LocalTime examStartTime;
    private ExamSectionTransfer examSectionTransfer;

    public ExaminprogressResponse(
            boolean examTokenNotFound,
            boolean examNotFound,
            boolean examHasStarted,
            boolean examComplete,
            boolean examExpired,
            boolean timedPerExam,
            boolean timedPerSection,
            boolean timedPerQuestion,
            boolean pausable,
            boolean paused,
            Long examTime,
            LocalDate examStartDate,
            LocalTime examStartTime,
            ExamSectionTransfer examSectionTransfer ) {

        super();
        this.examTokenNotFound = examTokenNotFound;
        this.examNotFound = examNotFound;
        this.examHasStarted = examHasStarted;
        this.examComplete = examComplete;
        this.examExpired = examExpired;
        this.timedPerExam = timedPerExam;
        this.timedPerSection = timedPerSection;
        this.timedPerQuestion = timedPerQuestion;
        this.examTime = examTime;
        this.pausable = pausable;
        this.paused = paused;
        this.examStartDate = examStartDate;
        this.examStartTime = examStartTime;
        this.examSectionTransfer = examSectionTransfer;
    }


    public boolean isExamTokenNotFound() {

        return examTokenNotFound;
    }


    public void setExamTokenNotFound( boolean examTokenNotFound ) {

        this.examTokenNotFound = examTokenNotFound;
    }


    public boolean isExamNotFound() {

        return examNotFound;
    }


    public void setExamNotFound( boolean examNotFound ) {

        this.examNotFound = examNotFound;
    }


    public boolean isExamHasStarted() {

        return examHasStarted;
    }


    public void setExamHasStarted( boolean examHasStarted ) {

        this.examHasStarted = examHasStarted;
    }


    public ExamSectionTransfer getExamSectionTransfer() {

        return examSectionTransfer;
    }


    public void setExamSectionTransfer( ExamSectionTransfer examSectionTransfer ) {

        this.examSectionTransfer = examSectionTransfer;
    }


    public boolean isExamComplete() {

        return examComplete;
    }


    public void setExamComplete( boolean examComplete ) {

        this.examComplete = examComplete;
    }


    public boolean isExamExpired() {

        return examExpired;
    }


    public void setExamExpired( boolean examExpired ) {

        this.examExpired = examExpired;
    }


    public boolean isTimedPerExam() {

        return timedPerExam;
    }


    public void setTimedPerExam( boolean timedPerExam ) {

        this.timedPerExam = timedPerExam;
    }


    public boolean isTimedPerSection() {

        return timedPerSection;
    }


    public void setTimedPerSection( boolean timedPerSection ) {

        this.timedPerSection = timedPerSection;
    }


    public boolean isTimedPerQuestion() {

        return timedPerQuestion;
    }


    public void setTimedPerQuestion( boolean timedPerQuestion ) {

        this.timedPerQuestion = timedPerQuestion;
    }


    public Long getExamTime() {

        return examTime;
    }


    public void setExamTime( Long examTime ) {

        this.examTime = examTime;
    }


    public LocalDate getExamStartDate() {

        return examStartDate;
    }


    public void setExamStartDate( LocalDate examStartDate ) {

        this.examStartDate = examStartDate;
    }


    public LocalTime getExamStartTime() {

        return examStartTime;
    }


    public void setExamStartTime( LocalTime examStartTime ) {

        this.examStartTime = examStartTime;
    }


    public boolean isPausable() {

        return pausable;
    }


    public void setPausable( boolean pausable ) {

        this.pausable = pausable;
    }


    public boolean isPaused() {

        return paused;
    }


    public void setPaused( boolean paused ) {

        this.paused = paused;
    }
}
