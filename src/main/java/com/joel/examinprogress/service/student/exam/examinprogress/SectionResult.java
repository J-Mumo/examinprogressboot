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

    private String sectionName;
    private Float percentScore;

    public SectionResult( String sectionName, Float percentScore ) {

        super();
        this.sectionName = sectionName;
        this.percentScore = percentScore;
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
