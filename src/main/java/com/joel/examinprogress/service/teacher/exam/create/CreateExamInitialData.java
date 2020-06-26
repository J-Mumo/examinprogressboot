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
package com.joel.examinprogress.service.teacher.exam.create;

import com.joel.examinprogress.service.teacher.exam.shared.ExamTimerTypeTransfer;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
public class CreateExamInitialData {

    private ExamTimerTypeTransfer[] examTimerTypeTransfers;

    public CreateExamInitialData( ExamTimerTypeTransfer[] examTimerTypeTransfers ) {

        super();
        this.examTimerTypeTransfers = examTimerTypeTransfers;
    }


    public ExamTimerTypeTransfer[] getExamTimerTypeTransfers() {

        return examTimerTypeTransfers;
    }


    public void setExamTimerTypeTransfers( ExamTimerTypeTransfer[] examTimerTypeTransfers ) {

        this.examTimerTypeTransfers = examTimerTypeTransfers;
    }
}
