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

import com.joel.examinprogress.service.teacher.exam.section.question.shared.MultipleChoiceQuestionAnswerRequest;

/**
 * @author Joel Mumo
 * @date   1st July, 2020
 */
public class QuestionRequest {

    private String questionText;
    private Integer score;
    private MultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests;

    public QuestionRequest(
            String questionText,
            Integer score,
            MultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests ) {

        super();
        this.questionText = questionText;
        this.score = score;
        this.addMultipleChoiceQuestionAnswerRequests = addMultipleChoiceQuestionAnswerRequests;
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


    public MultipleChoiceQuestionAnswerRequest[] getAddMultipleChoiceQuestionAnswerRequests() {

        return addMultipleChoiceQuestionAnswerRequests;
    }


    public void setAddMultipleChoiceQuestionAnswerRequests(
            MultipleChoiceQuestionAnswerRequest[] addMultipleChoiceQuestionAnswerRequests ) {

        this.addMultipleChoiceQuestionAnswerRequests = addMultipleChoiceQuestionAnswerRequests;
    }
}
