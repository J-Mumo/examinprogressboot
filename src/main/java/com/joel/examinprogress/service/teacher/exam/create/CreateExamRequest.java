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
package com.joel.examinprogress.service.teacher.exam.create;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class CreateExamRequest {

    private String name;
    private String description;
    private String duration;
    private Long examTimerTypeId;

    public CreateExamRequest(
            String name,
            String description,
            String duration,
            Long examTimerTypeId ) {

        super();
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.examTimerTypeId = examTimerTypeId;
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


    public String getDuration() {

        return duration;
    }


    public void setDuration( String duration ) {

        this.duration = duration;
    }


    public Long getExamTimerTypeId() {

        return examTimerTypeId;
    }


    public void setExamTimerTypeId( Long examTimerTypeId ) {

        this.examTimerTypeId = examTimerTypeId;
    }
}
