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
package com.joel.examinprogress.domain.exam;

/**
 * @author Joel Mumo
 * @date   25th June, 2020
 */
public enum ExamTimerTypeEnum {

    TIMED_PER_EXAM( 1l, "Timed per Exam" ),
    TIMED_PER_SECTION( 2l, "Timed per Section" ),
    TIMED_PER_QUESTION( 3l, "Timed per Question" );

    private Long examTimerTypeId;
    private String name;

    private ExamTimerTypeEnum( Long examTimerTypeId, String name ) {

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
