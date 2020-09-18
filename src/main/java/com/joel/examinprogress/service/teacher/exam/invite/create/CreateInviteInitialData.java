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
package com.joel.examinprogress.service.teacher.exam.invite.create;

/**
 * @author Joel Mumo
 * @date   18th Sept, 2020
 */
public class CreateInviteInitialData {

    private Boolean timedPerExam;
    private Boolean timedPerSection;
    private Boolean timedPerQuestion;

    public CreateInviteInitialData(
            Boolean timedPerExam,
            Boolean timedPerSection,
            Boolean timedPerQuestion ) {

        super();
        this.timedPerExam = timedPerExam;
        this.timedPerSection = timedPerSection;
        this.timedPerQuestion = timedPerQuestion;
    }


    public Boolean getTimedPerExam() {

        return timedPerExam;
    }


    public void setTimedPerExam( Boolean timedPerExam ) {

        this.timedPerExam = timedPerExam;
    }


    public Boolean getTimedPerSection() {

        return timedPerSection;
    }


    public void setTimedPerSection( Boolean timedPerSection ) {

        this.timedPerSection = timedPerSection;
    }


    public Boolean getTimedPerQuestion() {

        return timedPerQuestion;
    }


    public void setTimedPerQuestion( Boolean timedPerQuestion ) {

        this.timedPerQuestion = timedPerQuestion;
    }
}
