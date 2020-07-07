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
package com.joel.examinprogress.service.teacher.exam.section.question.edit;

import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceAnswerTransfer;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
public class EditQuestionInitialData {

    private boolean comprehensionQuestion;
    private String question;
    private String duration;
    private Integer score;
    private MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers;

    public EditQuestionInitialData(
            boolean comprehensionQuestion,
            String question,
            String duration,
            Integer score,
            MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers ) {

        super();
        this.comprehensionQuestion = comprehensionQuestion;
        this.question = question;
        this.duration = duration;
        this.score = score;
        this.multipleChoiceAnswerTransfers = multipleChoiceAnswerTransfers;
    }


    public boolean isComprehensionQuestion() {

        return comprehensionQuestion;
    }


    public void setComprehensionQuestion( boolean comprehensionQuestion ) {

        this.comprehensionQuestion = comprehensionQuestion;
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


    public Integer getScore() {

        return score;
    }


    public void setScore( Integer score ) {

        this.score = score;
    }


    public MultipleChoiceAnswerTransfer[] getMultipleChoiceAnswerTransfers() {

        return multipleChoiceAnswerTransfers;
    }


    public void setMultipleChoiceAnswerTransfers(
            MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers ) {

        this.multipleChoiceAnswerTransfers = multipleChoiceAnswerTransfers;
    }
}
