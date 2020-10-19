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
package com.joel.examinprogress.service.teacher.results.sectionperformance;


/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class AnswerResult {

    private String answer;
    private boolean selected;

    public AnswerResult( String answer, boolean selected ) {

        super();
        this.answer = answer;
        this.selected = selected;
    }


    /**
     * @return the answer
     */
    public String getAnswer() {

        return answer;
    }


    /**
     * @param answer the answer to set
     */
    public void setAnswer( String answer ) {

        this.answer = answer;
    }


    /**
     * @return the selected
     */
    public boolean isSelected() {

        return selected;
    }


    /**
     * @param selected the selected to set
     */
    public void setSelected( boolean selected ) {

        this.selected = selected;
    }
}
