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
package com.joel.examinprogress.service.teacher.exam.view;

import java.time.LocalDateTime;

import com.joel.examinprogress.service.teacher.exam.section.shared.SectionTransfer;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
public class ViewExamInitialData {

    private String name;
    private String description;
    private LocalDateTime startTime;
    private String duration;
    private boolean complete;
    private SectionTransfer[] sectionTransfers;

    public ViewExamInitialData(
            String name,
            String description,
            LocalDateTime startTime,
            String duration,
            boolean complete,
            SectionTransfer[] sectionTransfers ) {

        super();
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
        this.complete = complete;
        this.sectionTransfers = sectionTransfers;
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


    public LocalDateTime getStartTime() {

        return startTime;
    }


    public void setStartTime( LocalDateTime startTime ) {

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


    public SectionTransfer[] getSectionTransfers() {

        return sectionTransfers;
    }


    public void setSectionTransfers( SectionTransfer[] sectionTransfers ) {

        this.sectionTransfers = sectionTransfers;
    }
}