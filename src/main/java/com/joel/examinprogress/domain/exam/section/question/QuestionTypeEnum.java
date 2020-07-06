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
package com.joel.examinprogress.domain.exam.section.question;

/**
 * @author Joel Mumo
 * @date   5th July, 2020
 */
public enum QuestionTypeEnum {

    QUESTION( 1l, "Question" ),
    COMPREHENSION_QUESTION( 2l, "Comprehension question" );

    private Long questionTypeId;
    private String name;

    private QuestionTypeEnum( Long questionTypeId, String name ) {

        this.questionTypeId = questionTypeId;
        this.name = name;
    }


    public Long getQuestionTypeId() {

        return questionTypeId;
    }


    public void setQuestionTypeId( Long questionTypeId ) {

        this.questionTypeId = questionTypeId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }
}
