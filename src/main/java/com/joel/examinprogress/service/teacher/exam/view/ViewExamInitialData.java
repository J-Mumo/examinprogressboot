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

import com.joel.examinprogress.service.teacher.exam.shared.SectionTransfer;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
public class ViewExamInitialData {

    private String name;
    private String description;
    private String duration;
    private SectionTransfer[] sectionTransfers;
    private boolean hasInvites;
    private Long inviteId;

    public ViewExamInitialData(
            String name,
            String description,
            String duration,
            SectionTransfer[] sectionTransfers,
            boolean hasInvites,
            Long inviteId ) {

        super();
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.sectionTransfers = sectionTransfers;
        this.hasInvites = hasInvites;
        this.inviteId = inviteId;
    }


    /**
     * @return the inviteId
     */
    public Long getInviteId() {

        return inviteId;
    }


    /**
     * @param inviteId the inviteId to set
     */
    public void setInviteId( Long inviteId ) {

        this.inviteId = inviteId;
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


    public SectionTransfer[] getSectionTransfers() {

        return sectionTransfers;
    }


    public void setSectionTransfers( SectionTransfer[] sectionTransfers ) {

        this.sectionTransfers = sectionTransfers;
    }


    public boolean isHasInvites() {

        return hasInvites;
    }


    public void setHasInvites( boolean hasInvites ) {

        this.hasInvites = hasInvites;
    }
}
