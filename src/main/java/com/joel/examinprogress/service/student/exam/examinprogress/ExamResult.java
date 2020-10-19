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
 * @date   Oct 5, 2020
 */

public class ExamResult {

    private boolean completeResult;
    private String examName;
    private Float percentScore;
    private Integer pointsEarned;
    private Integer examTotalPoints;
    private SectionResult[] sectionResults;

    public ExamResult( boolean completeResult,
            String examName,
            Float percentScore,
            Integer pointsEarned,
            Integer examTotalPoints,
            SectionResult[] sectionResults ) {

        super();
        this.completeResult = completeResult;
        this.examName = examName;
        this.pointsEarned = pointsEarned;
        this.examTotalPoints = examTotalPoints;
        this.percentScore = percentScore;
        this.sectionResults = sectionResults;
    }



    /**
     * @return the examName
     */
    public String getExamName() {

        return examName;
    }



    /**
     * @param examName the examName to set
     */
    public void setExamName( String examName ) {

        this.examName = examName;
    }


    /**
     * @return the pointsEarned
     */
    public Integer getPointsEarned() {

        return pointsEarned;
    }


    /**
     * @param pointsEarned the pointsEarned to set
     */
    public void setPointsEarned( Integer pointsEarned ) {

        this.pointsEarned = pointsEarned;
    }


    /**
     * @return the examTotalPoints
     */
    public Integer getExamTotalPoints() {

        return examTotalPoints;
    }


    /**
     * @param examTotalPoints the examTotalPoints to set
     */
    public void setExamTotalPoints( Integer examTotalPoints ) {

        this.examTotalPoints = examTotalPoints;
    }


    public boolean isCompleteResult() {

        return completeResult;
    }


    public void setCompleteResult( boolean completeResult ) {

        this.completeResult = completeResult;
    }


    public Float getPercentScore() {

        return percentScore;
    }


    public void setPercentScore( Float percentScore ) {

        this.percentScore = percentScore;
    }


    public SectionResult[] getSectionResults() {

        return sectionResults;
    }


    public void setSectionResults( SectionResult[] sectionResults ) {

        this.sectionResults = sectionResults;
    }
}
