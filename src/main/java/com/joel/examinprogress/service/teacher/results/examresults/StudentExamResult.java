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
package com.joel.examinprogress.service.teacher.results.examresults;


/**
 * @author Joel Mumo
 * @date   Oct 8, 2020
 */

public class StudentExamResult {

    private Long studentId;
    private String studentName;
    private Float percentScore;
    private Boolean examInProgress;
    private Boolean viewPerformance;
    private Boolean finalizeScoring;

    public StudentExamResult(
            Long studentId,
            String studentName,
            Float percentScore,
            Boolean examInProgress,
            Boolean viewPerformance,
            Boolean finalizeScoring ) {

        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.percentScore = percentScore;
        this.examInProgress = examInProgress;
        this.viewPerformance = viewPerformance;
        this.finalizeScoring = finalizeScoring;
    }


    public Long getStudentId() {

        return studentId;
    }


    public void setStudentId( Long studentId ) {

        this.studentId = studentId;
    }


    public Boolean getExamInProgress() {

        return examInProgress;
    }


    public void setExamInProgress( Boolean examInProgress ) {

        this.examInProgress = examInProgress;
    }


    public String getStudentName() {

        return studentName;
    }


    public void setStudentName( String studentName ) {

        this.studentName = studentName;
    }


    public Float getPercentScore() {

        return percentScore;
    }


    public void setPercentScore( Float percentScore ) {

        this.percentScore = percentScore;
    }


    public Boolean getViewPerformance() {

        return viewPerformance;
    }


    public void setViewPerformance( Boolean viewPerformance ) {

        this.viewPerformance = viewPerformance;
    }


    public Boolean getFinalizeScoring() {

        return finalizeScoring;
    }


    public void setFinalizeScoring( Boolean finalizeScoring ) {

        this.finalizeScoring = finalizeScoring;
    }
}
