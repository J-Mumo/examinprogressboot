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
 * @date   16th Aug, 2020
 */
public class MultipleChoiceAnswerRequest {

    private Long examTokenId;
    private Long questionId;
    private Long[] answerIds;

    public MultipleChoiceAnswerRequest( Long examTokenId, Long questionId, Long[] answerIds ) {

        super();
        this.examTokenId = examTokenId;
        this.questionId = questionId;
        this.answerIds = answerIds;
    }


    public Long getExamTokenId() {

        return examTokenId;
    }


    public void setExamTokenId( Long examTokenId ) {

        this.examTokenId = examTokenId;
    }


    public Long getQuestionId() {

        return questionId;
    }


    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
    }


    public Long[] getAnswerIds() {

        return answerIds;
    }


    public void setAnswerIds( Long[] answerIds ) {

        this.answerIds = answerIds;
    }
}
