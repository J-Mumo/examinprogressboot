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
package com.joel.examinprogress.service.teacher.exam.invite.view;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
public class ExamTokenTransfer {

    private Long examTokenId;
    private String email;

    public ExamTokenTransfer( Long examTokenId, String email ) {

        super();
        this.examTokenId = examTokenId;
        this.email = email;
    }


    public Long getExamTokenId() {

        return examTokenId;
    }


    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }
}
