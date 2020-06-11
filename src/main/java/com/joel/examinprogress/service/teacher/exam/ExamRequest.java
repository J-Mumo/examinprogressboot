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

import java.time.Duration;
import java.util.Calendar;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class ExamRequest {

    private String name;
    private String description;
    private Calendar startTime;
    private Duration duration;
    private boolean complete;

    public ExamRequest(
            String name,
            String description,
            Calendar startTime,
            Duration duration,
            boolean complete ) {

        super();
        this.name = name;
        this.description = description;
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


    public Calendar getStartTime() {

        return startTime;
    }


    public void setStartTime( Calendar startTime ) {

        this.startTime = startTime;
    }


    public Duration getDuration() {

        return duration;
    }


    public void setDuration( Duration duration ) {

        this.duration = duration;
    }


    public boolean isComplete() {

        return complete;
    }


    public void setComplete( boolean complete ) {

        this.complete = complete;
    }
}
