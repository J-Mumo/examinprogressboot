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
package com.joel.examinprogress.service.teacher.exam;

import java.util.Date;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class ExamRequest {

    private String name;
    private String description;
    private Date startDate;
    private String startTime;
    private String duration;
    private boolean complete;

    public ExamRequest(
            String name,
            String description,
            Date startDate,
            String startTime,
            String duration,
            boolean complete ) {

        super();
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.complete = complete;
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


    public Date getStartDate() {

        return startDate;
    }


    public void setStartDate( Date startDate ) {

        this.startDate = startDate;
    }


    public String getStartTime() {

        return startTime;
    }


    public void setStartTime( String startTime ) {

        this.startTime = startTime;
    }


    public String getDuration() {

        return duration;
    }


    public void setDuration( String duration ) {

        this.duration = duration;
    }


    public boolean isComplete() {

        return complete;
    }


    public void setComplete( boolean complete ) {

        this.complete = complete;
    }
}
