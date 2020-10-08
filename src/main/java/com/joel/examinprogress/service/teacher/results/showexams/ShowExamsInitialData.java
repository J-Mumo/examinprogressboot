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
package com.joel.examinprogress.service.teacher.results.showexams;

import com.joel.examinprogress.service.teacher.exam.exams.ExamTransfer;

/**
 * @author Joel Mumo
 * @date   Oct 8, 2020
 */

public class ShowExamsInitialData {

    private ExamTransfer[] examTransfers;

    public ShowExamsInitialData( ExamTransfer[] examTransfers ) {

        super();
        this.examTransfers = examTransfers;
    }


    public ExamTransfer[] getExamTransfers() {

        return examTransfers;
    }


    public void setExamTransfers( ExamTransfer[] examTransfers ) {

        this.examTransfers = examTransfers;
    }
}
