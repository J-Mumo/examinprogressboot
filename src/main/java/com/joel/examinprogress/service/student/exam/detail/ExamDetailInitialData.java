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
package com.joel.examinprogress.service.student.exam.detail;

import java.time.LocalTime;
import java.util.Date;

/**
 * @author Joel Mumo
 * @date   10th Aug, 2020
 */
public class ExamDetailInitialData {

    private boolean examExists;
    private boolean studentRegistered;
    private boolean examHasStarted;
    private boolean examHasEnded;
    private Long examTokenId;
    private String examName;
    private String examDescription;
    private Date startDate;
    private Date endDate;
    private LocalTime startTime;
    private Long examTotalTime;
    private boolean pausable;
    private boolean timedPerExam;
    private boolean timedPerSection;
    private boolean timedPerQuestion;
    private String email;

    public ExamDetailInitialData(
            boolean examExists,
            boolean studentRegistered,
            boolean examHasStarted,
            boolean examHasEnded,
            Long examTokenId,
            String examName,
            String examDescription,
            Date startDate,
            Date endDate,
            LocalTime startTime,
            Long examTotalTime,
            boolean pausable,
            boolean timedPerExam,
            boolean timedPerSection,
            boolean timedPerQuestion,
            String email ) {

        super();
        this.examExists = examExists;
        this.studentRegistered = studentRegistered;
        this.examHasStarted = examHasStarted;
        this.examHasEnded = examHasEnded;
        this.examTokenId = examTokenId;
        this.examName = examName;
        this.examDescription = examDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.examTotalTime = examTotalTime;
        this.pausable = pausable;
        this.timedPerExam = timedPerExam;
        this.timedPerSection = timedPerSection;
        this.timedPerQuestion = timedPerQuestion;
        this.email = email;
    }


    public boolean isExamExists() {

        return examExists;
    }


    public void setExamExists( boolean examExists ) {

        this.examExists = examExists;
    }


    public boolean isStudentRegistered() {

        return studentRegistered;
    }


    public void setStudentRegistered( boolean studentRegistered ) {

        this.studentRegistered = studentRegistered;
    }


    public boolean isExamHasStarted() {

        return examHasStarted;
    }


    public void setExamHasStarted( boolean examHasStarted ) {

        this.examHasStarted = examHasStarted;
    }


    public boolean isExamHasEnded() {

        return examHasEnded;
    }


    public void setExamHasEnded( boolean examHasEnded ) {

        this.examHasEnded = examHasEnded;
    }


    public Long getExamTokenId() {

        return examTokenId;
    }


    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    public String getExamName() {

        return examName;
    }


    public void setExamName( String examName ) {

        this.examName = examName;
    }


    public String getExamDescription() {

        return examDescription;
    }


    public void setExamDescription( String examDescription ) {

        this.examDescription = examDescription;
    }


    public Date getStartDate() {

        return startDate;
    }


    public void setStartDate( Date startDate ) {

        this.startDate = startDate;
    }


    public Date getEndDate() {

        return endDate;
    }


    public void setEndDate( Date endDate ) {

        this.endDate = endDate;
    }


    public LocalTime getStartTime() {

        return startTime;
    }


    public void setStartTime( LocalTime startTime ) {

        this.startTime = startTime;
    }


    public Long getExamTotalTime() {

        return examTotalTime;
    }


    public void setExamTotalTime( Long examTotalTime ) {

        this.examTotalTime = examTotalTime;
    }


    public boolean isPausable() {

        return pausable;
    }


    public void setPausable( boolean pausable ) {

        this.pausable = pausable;
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


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }
}
