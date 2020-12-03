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
package com.joel.examinprogress.service.teacher.token.tokenconsumption;


/**
 * @author Joel Mumo
 * @date   Dec 3, 2020
 */
public class TokenConsumedTransfer {

    private Long id;
    private String email;
    private String examName;
    private String studentName;

    public TokenConsumedTransfer( Long id, String email, String examName, String studentName ) {

        super();
        this.id = id;
        this.email = email;
        this.examName = examName;
        this.studentName = studentName;
    }


    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId( Long id ) {

        this.id = id;
    }


    /**
     * @return the email
     */
    public String getEmail() {

        return email;
    }


    /**
     * @param email the email to set
     */
    public void setEmail( String email ) {

        this.email = email;
    }


    /**
     * @return the examName
     */
    public String getExamName() {

        return examName;
    }


    /**
     * @param examName the examName to set
     */
    public void setExamName( String examName ) {

        this.examName = examName;
    }


    /**
     * @return the studentName
     */
    public String getStudentName() {

        return studentName;
    }


    /**
     * @param studentName the studentName to set
     */
    public void setStudentName( String studentName ) {

        this.studentName = studentName;
    }
}
