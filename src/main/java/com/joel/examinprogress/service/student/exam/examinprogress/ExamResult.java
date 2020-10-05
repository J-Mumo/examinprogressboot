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
    private Float percentScore;
    private SectionResult[] sectionResults;

    public ExamResult( boolean completeResult,
            Float percentScore, SectionResult[] sectionResults ) {

        super();
        this.completeResult = completeResult;
        this.percentScore = percentScore;
        this.sectionResults = sectionResults;
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
