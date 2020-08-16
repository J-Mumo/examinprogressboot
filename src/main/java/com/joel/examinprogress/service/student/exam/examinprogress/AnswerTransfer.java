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
public class AnswerTransfer {

    private Long answerId;
    private String answer;

    public AnswerTransfer( Long answerId, String answer ) {

        super();
        this.answerId = answerId;
        this.answer = answer;
    }


    public Long getAnswerId() {

        return answerId;
    }


    public void setAnswerId( Long answerId ) {

        this.answerId = answerId;
    }


    public String getAnswer() {

        return answer;
    }


    public void setAnswer( String answer ) {

        this.answer = answer;
    }
}
