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

public class SectionResult {

    private Long sectionId;
    private String sectionName;
    private Float percentScore;
    private Integer pointsEarned;
    private Integer sectionTotalPoints;

    public SectionResult( Long sectionId,
            String sectionName,
            Float percentScore,
            Integer pointsEarned,
            Integer sectionTotalPoints ) {

        super();
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.percentScore = percentScore;
        this.pointsEarned = pointsEarned;
        this.sectionTotalPoints = sectionTotalPoints;
    }


    /**
     * @return the sectionId
     */
    public Long getSectionId() {

        return sectionId;
    }


    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
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


    public String getSectionName() {

        return sectionName;
    }


    public void setSectionName( String sectionName ) {

        this.sectionName = sectionName;
    }


    public Float getPercentScore() {

        return percentScore;
    }


    public void setPercentScore( Float percentScore ) {

        this.percentScore = percentScore;
    }
}
