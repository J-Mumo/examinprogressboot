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
package com.joel.examinprogress.service.student.exam.exams;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
public class StudentExamTransfer {

    private Long examId;
    private String name;
    private String description;
    private Long examTokenId;
    private boolean examinprogress;
    private boolean examNotStarted;
    private boolean viewResults;
    private String token;

    public StudentExamTransfer( Long examId, String name, String description,
            Long examTokenId, boolean examinprogress, boolean examNotStarted,
            boolean viewResults, String token ) {

        super();
        this.examId = examId;
        this.name = name;
        this.description = description;
        this.examTokenId = examTokenId;
        this.examinprogress = examinprogress;
        this.examNotStarted = examNotStarted;
        this.viewResults = viewResults;
        this.token = token;
    }


    
    /**
     * @return the token
     */
    public String getToken() {
    
        return token;
    }


    
    /**
     * @param token the token to set
     */
    public void setToken( String token ) {
    
        this.token = token;
    }


    /**
     * @return the examTokenId
     */
    public Long getExamTokenId() {

        return examTokenId;
    }


    /**
     * @param examTokenId the examTokenId to set
     */
    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    /**
     * @return the examinprogress
     */
    public boolean isExaminprogress() {

        return examinprogress;
    }


    /**
     * @param examinprogress the examinprogress to set
     */
    public void setExaminprogress( boolean examinprogress ) {

        this.examinprogress = examinprogress;
    }


    /**
     * @return the examNotStarted
     */
    public boolean isExamNotStarted() {

        return examNotStarted;
    }


    /**
     * @param examNotStarted the examNotStarted to set
     */
    public void setExamNotStarted( boolean examNotStarted ) {

        this.examNotStarted = examNotStarted;
    }


    /**
     * @return the viewResults
     */
    public boolean isViewResults() {

        return viewResults;
    }


    /**
     * @param viewResults the viewResults to set
     */
    public void setViewResults( boolean viewResults ) {

        this.viewResults = viewResults;
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
