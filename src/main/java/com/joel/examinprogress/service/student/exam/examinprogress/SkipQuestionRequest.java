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
 * @date   17th Aug, 2020
 */
public class SkipQuestionRequest {

    private Long examTokenId;
    private Long questionId;

    public SkipQuestionRequest( Long examTokenId, Long questionId ) {

        super();
        this.examTokenId = examTokenId;
        this.questionId = questionId;
    }


    public Long getExamTokenId() {

        return examTokenId;
    }


    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    public Long getQuestionId() {

        return questionId;
    }


    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
    }
}
