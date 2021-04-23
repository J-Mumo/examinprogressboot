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
 * @date   Apr 23, 2021
 */
public class TerminatedResponse {

    private Boolean terminated;

    public TerminatedResponse( Boolean terminated ) {

        super();
        this.terminated = terminated;
    }


    /**
     * @return the terminated
     */
    public Boolean getTerminated() {

        return terminated;
    }


    /**
     * @param terminated the terminated to set
     */
    public void setTerminated( Boolean terminated ) {

        this.terminated = terminated;
    }
}
