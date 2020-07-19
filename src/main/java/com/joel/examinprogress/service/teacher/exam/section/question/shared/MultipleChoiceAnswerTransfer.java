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
    private String answerText;
    private boolean correct;
    private String answerType;

    public MultipleChoiceAnswerTransfer( Long answerId, String answerText, boolean correct,
            String answerType ) {

        super();
        this.answerId = answerId;
        this.answerText = answerText;
        this.correct = correct;
        this.answerType = answerType;
    }


    public Long getAnswerId() {

        return answerId;
    }


    public void setAnswerId( Long answerId ) {

        this.answerId = answerId;
    }


    public String getAnswerText() {

        return answerText;
    }


    public void setAnswerText( String answerText ) {

        this.answerText = answerText;
    }


    public boolean isCorrect() {

        return correct;
    }


    public void setCorrect( boolean correct ) {

        this.correct = correct;
    }


    public String getAnswerType() {

        return answerType;
    }


    public void setAnswerType( String answerType ) {

        this.answerType = answerType;
    }
}
