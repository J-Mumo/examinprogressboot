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
package com.joel.examinprogress.service.teacher.exam.section.question.shared;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
public class MultipleChoiceAnswerTransfer {

    private Long answerId;
    private String answerTxt;

    public MultipleChoiceAnswerTransfer( Long answerId, String answerTxt ) {

        super();
        this.answerId = answerId;
        this.answerTxt = answerTxt;
    }


    public Long getAnswerId() {

        return answerId;
    }


    public void setAnswerId( Long answerId ) {

        this.answerId = answerId;
    }


    public String getAnswerTxt() {

        return answerTxt;
    }


    public void setAnswerTxt( String answerTxt ) {

        this.answerTxt = answerTxt;
    }
}
