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
public class ExamQuestionTransfer {

    private Long questionId;
    private boolean comprehensionQuestion;
    private String question;
    private Long questionTime;
    private ExamQuestionTransfer questionTransfer;
    private String answerType;
    private AnswerTransfer[] answerTransfers;

    public ExamQuestionTransfer( Long questionId, boolean comprehensionQuestion, String question,
            Long questionTime, ExamQuestionTransfer questionTransfer, String answerType,
            AnswerTransfer[] answerTransfers ) {

        super();
        this.questionId = questionId;
        this.comprehensionQuestion = comprehensionQuestion;
        this.question = question;
        this.questionTime = questionTime;
        this.questionTransfer = questionTransfer;
        this.answerType = answerType;
        this.answerTransfers = answerTransfers;
    }


    public Long getQuestionId() {

        return questionId;
    }


    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
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


    public Long getQuestionTime() {

        return questionTime;
    }


    public void setQuestionTime( Long questionTime ) {

        this.questionTime = questionTime;
    }


    public ExamQuestionTransfer getQuestionTransfer() {

        return questionTransfer;
    }


    public void setQuestionTransfer( ExamQuestionTransfer questionTransfer ) {

        this.questionTransfer = questionTransfer;
    }


    public String getAnswerType() {

        return answerType;
    }


    public void setAnswerType( String answerType ) {

        this.answerType = answerType;
    }


    public AnswerTransfer[] getAnswerTransfers() {

        return answerTransfers;
    }


    public void setAnswerTransfers( AnswerTransfer[] answerTransfers ) {

        this.answerTransfers = answerTransfers;
    }
}
