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
 * @date   7th July, 2020
 */
public class ComprehensionQuestionTransfer {

    private String question;
    private String duration;
    private QuestionTransfer[] questionTransfers;

    public ComprehensionQuestionTransfer(
            String question,
            String duration,
            QuestionTransfer[] questionTransfers ) {

        super();
        this.question = question;
        this.duration = duration;
        this.questionTransfers = questionTransfers;
    }


    public String getQuestion() {

        return question;
    }


    public void setQuestion( String question ) {

        this.question = question;
    }


    public String getDuration() {

        return duration;
    }


    public void setDuration( String duration ) {

        this.duration = duration;
    }


    public QuestionTransfer[] getQuestionTransfers() {

        return questionTransfers;
    }


    public void setQuestionTransfers( QuestionTransfer[] questionTransfers ) {

        this.questionTransfers = questionTransfers;
    }
}
