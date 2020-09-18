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
package com.joel.examinprogress.service.student.exam.examinprogress;

/**
 * @author Joel Mumo
 * @date   9th Sep, 2020
 */
public class SkipSectionRequest {

    private Long examTokenId;
    private Long sectionId;
    private Boolean pause;

    public SkipSectionRequest( Long examTokenId, Long sectionId, Boolean pause ) {

        super();
        this.examTokenId = examTokenId;
        this.pause = pause;
        this.sectionId = sectionId;
    }


    public Long getExamTokenId() {

        return examTokenId;
    }


    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    public Long getSectionId() {

        return sectionId;
    }


    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
    }


    public Boolean getPause() {

        return pause;
    }


    public void setPause( Boolean pause ) {

        this.pause = pause;
    }

}
