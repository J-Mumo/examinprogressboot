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
 * @date   15th June, 2020
 */
public class MultipleChoiceQuestionAnswerRequest {

    private String answerText;
    private boolean correct;

    public MultipleChoiceQuestionAnswerRequest( String answerText, boolean correct ) {

        super();
        this.answerText = answerText;
        this.correct = correct;
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
}
