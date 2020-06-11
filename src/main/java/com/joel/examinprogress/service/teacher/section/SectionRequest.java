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
package com.joel.examinprogress.service.teacher.section;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class SectionRequest {

    private Long examId;
    private String name;

    public SectionRequest( Long examId, String name ) {

        super();
        this.examId = examId;
        this.name = name;
    }


    public Long getExamId() {

        return examId;
    }


    public void setExamId( Long examId ) {

        this.examId = examId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }
}
