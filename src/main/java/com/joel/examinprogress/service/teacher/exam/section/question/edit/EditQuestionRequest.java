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

import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceQuestionAnswerRequest;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
public class EditQuestionRequest {

    private Long questionId;
    private String questionText;
    private Integer score;
    private String duration;
    private String answerType;
    private MultipleChoiceQuestionAnswerRequest[] multipleChoiceQuestionAnswerRequests;

    public EditQuestionRequest(
            Long questionId,
            String questionText,
            Integer score,
            String duration,
            String answerType,
            MultipleChoiceQuestionAnswerRequest[] multipleChoiceQuestionAnswerRequests ) {

        super();
        this.questionId = questionId;
        this.questionText = questionText;
        this.score = score;
        this.duration = duration;
        this.answerType = answerType;
        this.multipleChoiceQuestionAnswerRequests = multipleChoiceQuestionAnswerRequests;
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


    public String getDuration() {

        return duration;
    }


    public void setDuration( String duration ) {

        this.duration = duration;
    }


    public String getAnswerType() {

        return answerType;
    }


    public void setAnswerType( String answerType ) {

        this.answerType = answerType;
    }


    public MultipleChoiceQuestionAnswerRequest[] getMultipleChoiceQuestionAnswerRequests() {

        return multipleChoiceQuestionAnswerRequests;
    }


    public void setMultipleChoiceQuestionAnswerRequests(
            MultipleChoiceQuestionAnswerRequest[] multipleChoiceQuestionAnswerRequests ) {

        this.multipleChoiceQuestionAnswerRequests = multipleChoiceQuestionAnswerRequests;
    }
}
