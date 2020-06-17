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
package com.joel.examinprogress.service.teacher.exam.exams;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
public class ExamTransfer {

    private Long examId;
    private String name;
    private String description;

    public ExamTransfer( Long examId, String name, String description ) {

        super();
        this.examId = examId;
        this.name = name;
        this.description = description;
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


    public String getDescription() {

        return description;
    }


    public void setDescription( String description ) {

        this.description = description;
    }
}
