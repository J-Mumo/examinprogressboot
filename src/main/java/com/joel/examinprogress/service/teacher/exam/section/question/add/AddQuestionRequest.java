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
package com.joel.examinprogress.service.teacher.exam.section.question.add;

/**
 * @author Joel Mumo
 * @date   15th June, 2020
 */
public class AddQuestionRequest {

    private Long sectionId;
    private Long answerTypeId;
    private String questionText;
    private Integer score;
    private String duration;
    private AddMultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests;

    public AddQuestionRequest(
            Long sectionId,
            Long answerTypeId,
            String questionText,
            Integer score,
            String duration,
            String answerType,
            AddMultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests ) {

        super();
        this.sectionId = sectionId;
        this.answerTypeId = answerTypeId;
        this.questionText = questionText;
        this.score = score;
        this.duration = duration;
        this.addMultipleChoiceQuestionAnswerRequests = addMultipleChoiceQuestionAnswerRequests;
    }


    public Long getSectionId() {

        return sectionId;
    }


    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
    }


    public Long getAnswerTypeId() {

        return answerTypeId;
    }


    public void setAnswerTypeId( Long answerTypeId ) {

        this.answerTypeId = answerTypeId;
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


    public AddMultipleChoiceQuestionAnswerRequest[] getAddMultipleChoiceQuestionAnswerRequests() {

        return addMultipleChoiceQuestionAnswerRequests;
    }


    public void setAddMultipleChoiceQuestionAnswerRequests(
            AddMultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests ) {

        this.addMultipleChoiceQuestionAnswerRequests = addMultipleChoiceQuestionAnswerRequests;
    }
}
