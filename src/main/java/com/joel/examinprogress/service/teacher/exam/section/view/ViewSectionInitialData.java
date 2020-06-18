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
package com.joel.examinprogress.service.teacher.exam.section.view;

import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.shared.MultipleChoiceQuestionTransfer;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
public class ViewSectionInitialData {

    private String name;
    private String description;
    private MultipleChoiceQuestionTransfer[] multipleChoiceQuestionTransfers;

    public ViewSectionInitialData(
            String name,
            String description,
            MultipleChoiceQuestionTransfer[] multipleChoiceQuestionTransfers ) {

        super();
        this.name = name;
        this.description = description;
        this.multipleChoiceQuestionTransfers = multipleChoiceQuestionTransfers;
    }


    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public String getDescription() {

        return description;
    }


    public void setDescription( String description ) {

        this.description = description;
    }


    public MultipleChoiceQuestionTransfer[] getMultipleChoiceQuestionTransfers() {

        return multipleChoiceQuestionTransfers;
    }


    public void setMultipleChoiceQuestionTransfers(
            MultipleChoiceQuestionTransfer[] multipleChoiceQuestionTransfers ) {

        this.multipleChoiceQuestionTransfers = multipleChoiceQuestionTransfers;
    }
}
