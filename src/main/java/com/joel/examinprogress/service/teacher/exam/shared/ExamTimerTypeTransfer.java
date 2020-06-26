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
package com.joel.examinprogress.service.teacher.exam.shared;

/**
 * @author Joel Mumo
 * @date   26th June, 2020
 */
public class ExamTimerTypeTransfer {

    private Long examTimerTypeId;
    private String name;

    public ExamTimerTypeTransfer( Long examTimerTypeId, String name ) {

        super();
        this.examTimerTypeId = examTimerTypeId;
        this.name = name;
    }


    public Long getExamTimerTypeId() {

        return examTimerTypeId;
    }


    public void setExamTimerTypeId( Long examTimerTypeId ) {

        this.examTimerTypeId = examTimerTypeId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }
}
