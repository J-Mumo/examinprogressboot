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
 * @date   12th Aug, 2020
 */
public class ExamSectionTransfer {

    private Long sectionId;
    private String sectionName;
    private String description;
    private Long sectionTime;
    private boolean sectionComplete;
    private ExamQuestionTransfer examQuestionTransfer;

    public ExamSectionTransfer( Long sectionId, String sectionName, String description,
            Long sectionTime, boolean sectionComplete, ExamQuestionTransfer examQuestionTransfer ) {

        super();
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.sectionTime = sectionTime;
        this.sectionComplete = sectionComplete;
        this.examQuestionTransfer = examQuestionTransfer;
    }


    public Long getSectionId() {

        return sectionId;
    }


    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
    }


    public String getSectionName() {

        return sectionName;
    }


    public void setSectionName( String sectionName ) {

        this.sectionName = sectionName;
    }


    public String getDescription() {

        return description;
    }


    public void setDescription( String description ) {

        this.description = description;
    }


    public Long getSectionTime() {

        return sectionTime;
    }


    public void setSectionTime( Long sectionTime ) {

        this.sectionTime = sectionTime;
    }


    public boolean isSectionComplete() {

        return sectionComplete;
    }


    public void setSectionComplete( boolean sectionComplete ) {

        this.sectionComplete = sectionComplete;
    }


    public ExamQuestionTransfer getExamQuestionTransfer() {

        return examQuestionTransfer;
    }


    public void setExamQuestionTransfer( ExamQuestionTransfer examQuestionTransfer ) {

        this.examQuestionTransfer = examQuestionTransfer;
    }
}
