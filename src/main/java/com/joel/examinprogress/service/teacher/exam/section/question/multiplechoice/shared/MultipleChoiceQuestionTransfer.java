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
package com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
public class MultipleChoiceQuestionTransfer {

    private Long questionId;
    private String questionText;
    private Integer score;
    private MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers;

    public MultipleChoiceQuestionTransfer(
            Long questionId,
            String questionTxt,
            Integer score,
            MultipleChoiceAnswerTransfer[] multipleChoiceAnswerTransfers ) {

        super();
        this.questionId = questionId;
        this.questionText = questionTxt;
        this.score = score;
        this.multipleChoiceAnswerTransfers = multipleChoiceAnswerTransfers;
    }


    public Long getQuestionId() {

        return questionId;
    }


    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
    }


    public String getQuestionText() {

        return questionText;
    }


    public void setQuestionText( String questionText ) {

        this.questionText = questionText;
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
