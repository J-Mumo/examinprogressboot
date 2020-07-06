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
package com.joel.examinprogress.domain.exam.section.question.answer;

/**
 * @author Joel Mumo
 * @date   5th July, 2020
 */
public enum AnswerTypeEnum {

    MULTIPLE_CHOICE_SINGLE_ANSWER( 1l, "Multiple choice single answer" ),
    MULTIPLE_CHOICE_MULTIPLE_ANSWERS( 2l, "Multiple choice multiple answers" ),
    TEXT_ANSWER( 3l, "Text Answer" ),
    IMAGE_ANSWER( 3l, "Image Answer" );

    private Long answerTypeId;
    private String name;

    private AnswerTypeEnum( Long answerTypeId, String name ) {

        this.answerTypeId = answerTypeId;
        this.name = name;
    }


    public Long getAnswerTypeId() {

        return answerTypeId;
    }


    public void setAnswerTypeId( Long answerTypeId ) {

        this.answerTypeId = answerTypeId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }
}
