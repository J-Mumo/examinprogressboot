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
package com.joel.examinprogress.domain.exam.result;


/**
 * @author Joel Mumo
 * @date   Sep 18, 2020
 */

public enum ResultTypeEnum {

    EXAM_RESULT( 1l, "Exam result" ),
    SECTION_RESULT( 2l, "Section result" ),
    QUESTION_RESULT( 3l, "Question result" );

    private Long resultTypeId;
    private String name;

    private ResultTypeEnum( Long resultTypeId, String name ) {

        this.resultTypeId = resultTypeId;
        this.name = name;
    }


    public Long getResultTypeId() {

        return resultTypeId;
    }


    public void setResultTypeId( Long resultTypeId ) {

        this.resultTypeId = resultTypeId;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }
}
