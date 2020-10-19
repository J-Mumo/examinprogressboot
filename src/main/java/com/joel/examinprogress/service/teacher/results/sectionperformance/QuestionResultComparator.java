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

import java.util.Comparator;

import org.springframework.stereotype.Component;

/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
@Component
public class QuestionResultComparator implements Comparator<QuestionResult> {

    @Override
    public int compare( QuestionResult o1, QuestionResult o2 ) {

        return o1.getQuestionId().compareTo( o2.getQuestionId() );
    }
}
