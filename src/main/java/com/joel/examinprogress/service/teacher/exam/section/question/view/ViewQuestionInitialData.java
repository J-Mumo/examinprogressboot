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
package com.joel.examinprogress.service.teacher.exam.section.question.view;

import com.joel.examinprogress.service.teacher.exam.section.question.shared.ComprehensionQuestionTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTransfer;

/**
 * @author Joel Mumo
 * @date   7th July, 2020
 */
public class ViewQuestionInitialData {

    private boolean comprehensionQuestion;
    private QuestionTransfer questionTransfer;
    private ComprehensionQuestionTransfer comprehensionQuestionTransfer;

    public ViewQuestionInitialData(
            boolean comprehensionQuestion,
            QuestionTransfer questionTransfer,
            ComprehensionQuestionTransfer comprehensionQuestionTransfer ) {

        super();
        this.comprehensionQuestion = comprehensionQuestion;
        this.questionTransfer = questionTransfer;
        this.comprehensionQuestionTransfer = comprehensionQuestionTransfer;
    }


    public boolean isComprehensionQuestion() {

        return comprehensionQuestion;
    }


    public void setComprehensionQuestion( boolean comprehensionQuestion ) {

        this.comprehensionQuestion = comprehensionQuestion;
    }


    public QuestionTransfer getQuestionTransfer() {

        return questionTransfer;
    }


    public void setQuestionTransfer( QuestionTransfer questionTransfer ) {

        this.questionTransfer = questionTransfer;
    }


    public ComprehensionQuestionTransfer getComprehensionQuestionTransfer() {

        return comprehensionQuestionTransfer;
    }


    public void setComprehensionQuestionTransfer(
            ComprehensionQuestionTransfer comprehensionQuestionTransfer ) {

        this.comprehensionQuestionTransfer = comprehensionQuestionTransfer;
    }
}
