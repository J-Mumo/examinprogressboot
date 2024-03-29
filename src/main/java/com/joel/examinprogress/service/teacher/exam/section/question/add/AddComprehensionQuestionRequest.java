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
 * @date   1st July, 2020
 */
public class AddComprehensionQuestionRequest {

    private Long sectionId;
    private Long comprehensionQuestionId;
    private Long answerTypeId;
    private String comprehension;
    private QuestionRequest questionRequest;

    public AddComprehensionQuestionRequest(
            Long sectionId,
            Long comprehensionQuestionId,
            Long answerTypeId,
            String comprehension,
            QuestionRequest questionRequest ) {

        super();
        this.sectionId = sectionId;
        this.comprehensionQuestionId = comprehensionQuestionId;
        this.answerTypeId = answerTypeId;
        this.comprehension = comprehension;
        this.questionRequest = questionRequest;
    }


    public Long getSectionId() {

        return sectionId;
    }


    public void setSectionId( Long sectionId ) {

        this.sectionId = sectionId;
    }


    public Long getComprehensionQuestionId() {

        return comprehensionQuestionId;
    }


    public void setComprehensionQuestionId( Long comprehensionQuestionId ) {

        this.comprehensionQuestionId = comprehensionQuestionId;
    }


    public Long getAnswerTypeId() {

        return answerTypeId;
    }


    public void setAnswerTypeId( Long answerTypeId ) {

        this.answerTypeId = answerTypeId;
    }


    public String getComprehension() {

        return comprehension;
    }


    public void setComprehension( String comprehension ) {

        this.comprehension = comprehension;
    }


    public QuestionRequest getQuestionRequest() {

        return questionRequest;
    }


    public void setQuestionRequest( QuestionRequest questionRequest ) {

        this.questionRequest = questionRequest;
    }
}
