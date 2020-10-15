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
package com.joel.examinprogress.service.teacher.results.finalizescoring;


/**
 * @author Joel Mumo
 * @date   Oct 15, 2020
 */
public class FinalizeScoringInitialData {

    private String studentName;
    private Boolean scoringComplete;
    private FinalizeScore finalizeScore;

    public FinalizeScoringInitialData(
            String studentName,
            Boolean scoringComplete,
            FinalizeScore finalizeScore ) {

        super();
        this.studentName = studentName;
        this.scoringComplete = scoringComplete;
        this.finalizeScore = finalizeScore;
    }


    public String getStudentName() {

        return studentName;
    }


    public void setStudentName( String studentName ) {

        this.studentName = studentName;
    }


    public Boolean getScoringComplete() {

        return scoringComplete;
    }


    public void setScoringComplete( Boolean scoringComplete ) {

        this.scoringComplete = scoringComplete;
    }


    public FinalizeScore getFinalizeScore() {

        return finalizeScore;
    }


    
    public void setFinalizeScore( FinalizeScore finalizeScore ) {

        this.finalizeScore = finalizeScore;
    }
}
