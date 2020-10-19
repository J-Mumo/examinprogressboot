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
package com.joel.examinprogress.service.teacher.results.sectionperformance;


/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class SectionPerformanceRequestInitialData {

    private Long sectionId;
    private Long studentId;

    public SectionPerformanceRequestInitialData( Long sectionId, Long studentId ) {

        super();
        this.sectionId = sectionId;
        this.studentId = studentId;
    }


    /**
     * @return the sectionId
     */
    public Long getSectionId() {

        return sectionId;
    }


    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
    }


    /**
     * @return the studentId
     */
    public Long getStudentId() {

        return studentId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setStudentId( Long studentId ) {

        this.studentId = studentId;
    }
}
