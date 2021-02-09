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
package com.joel.examinprogress.service.student.exam.result;

import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceInitialData;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceInitialData;

/**
 * @author Joel Mumo
 * @date   Feb 9, 2021
 */
public interface StudentResultService {

    ViewPerformanceInitialData getInitialData( Long examTokenId );


    SectionPerformanceInitialData getSectionResult( Long sectionId );
}
