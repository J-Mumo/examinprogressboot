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

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
public class ExaminprogressResponse {

    private ExamSectionTransfer examSectionTransfer;
    private boolean examComplete;
    private boolean timedPerExam;
    private boolean timedPerSection;
    private boolean timedPerQuestion;
    private Long examTime;

    public ExaminprogressResponse( ExamSectionTransfer examSectionTransfer, boolean examComplete,
            boolean timedPerExam, boolean timedPerSection, boolean timedPerQuestion,
            Long examTime ) {

        super();
        this.examSectionTransfer = examSectionTransfer;
        this.examComplete = examComplete;
        this.timedPerExam = timedPerExam;
        this.timedPerSection = timedPerSection;
        this.timedPerQuestion = timedPerQuestion;
        this.examTime = examTime;
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
}
