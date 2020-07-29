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
package com.joel.examinprogress.service.teacher.exam.invite.view;

import java.util.Comparator;

import org.springframework.stereotype.Component;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
@Component
public class ExamTokenTransferComparator implements Comparator<ExamTokenTransfer> {

    @Override
    public int compare( ExamTokenTransfer o1, ExamTokenTransfer o2 ) {

        return 1 * o1.getExamTokenId().compareTo( o2.getExamTokenId() );
    }
}
