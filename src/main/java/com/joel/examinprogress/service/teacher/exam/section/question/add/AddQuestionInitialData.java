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

import com.joel.examinprogress.service.teacher.exam.section.question.shared.AnswerTypeTransfer;
import com.joel.examinprogress.service.teacher.exam.section.question.shared.QuestionTypeTransfer;

/**
 * @author Joel Mumo
 * @date   4th July, 2020
 */
public class AddQuestionInitialData {

    private boolean examTimedByQuestion;
    private QuestionTypeTransfer[] questionTypeTransfers;
    private AnswerTypeTransfer[] answerTypeTransfers;

    public AddQuestionInitialData( boolean examTimedByQuestion,
            QuestionTypeTransfer[] questionTypeTransfers,
            AnswerTypeTransfer[] answerTypeTransfers ) {

        super();
        this.examTimedByQuestion = examTimedByQuestion;
        this.questionTypeTransfers = questionTypeTransfers;
        this.answerTypeTransfers = answerTypeTransfers;
    }


    public boolean isExamTimedByQuestion() {

        return examTimedByQuestion;
    }


    public void setExamTimedByQuestion( boolean examTimedByQuestion ) {

        this.examTimedByQuestion = examTimedByQuestion;
    }


    public QuestionTypeTransfer[] getQuestionTypeTransfers() {

        return questionTypeTransfers;
    }


    public void setQuestionTypeTransfers( QuestionTypeTransfer[] questionTypeTransfers ) {

        this.questionTypeTransfers = questionTypeTransfers;
    }


    public AnswerTypeTransfer[] getAnswerTypeTransfers() {

        return answerTypeTransfers;
    }


    public void setAnswerTypeTransfers( AnswerTypeTransfer[] answerTypeTransfers ) {

        this.answerTypeTransfers = answerTypeTransfers;
    }
}
