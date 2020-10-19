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
package com.joel.examinprogress.service.teacher.results.sectionperformance;


/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class SectionPerformanceInitialData {

    private String sectionName;
    private Float percentScore;
    private Integer pointsEarned;
    private Integer sectionTotalPoints;
    private QuestionResult[] questionResults;

    public SectionPerformanceInitialData( String sectionName,
            Float percentScore,
            Integer pointsEarned,
            Integer sectionTotalPoints,
            QuestionResult[] questionResults ) {

        super();
        this.sectionName = sectionName;
        this.percentScore = percentScore;
        this.pointsEarned = pointsEarned;
        this.sectionTotalPoints = sectionTotalPoints;
        this.questionResults = questionResults;
    }


    /**
     * @return the sectionName
     */
    public String getSectionName() {

        return sectionName;
    }


    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName( String sectionName ) {

        this.sectionName = sectionName;
    }


    /**
     * @return the percentScore
     */
    public Float getPercentScore() {

        return percentScore;
    }


    /**
     * @param percentScore the percentScore to set
     */
    public void setPercentScore( Float percentScore ) {

        this.percentScore = percentScore;
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
     * @return the sectionTotalPoints
     */
    public Integer getSectionTotalPoints() {

        return sectionTotalPoints;
    }


    /**
     * @param sectionTotalPoints the sectionTotalPoints to set
     */
    public void setSectionTotalPoints( Integer sectionTotalPoints ) {

        this.sectionTotalPoints = sectionTotalPoints;
    }


    /**
     * @return the questionResults
     */
    public QuestionResult[] getQuestionResults() {

        return questionResults;
    }


    /**
     * @param questionResults the questionResults to set
     */
    public void setQuestionResults( QuestionResult[] questionResults ) {

        this.questionResults = questionResults;
    }
}
