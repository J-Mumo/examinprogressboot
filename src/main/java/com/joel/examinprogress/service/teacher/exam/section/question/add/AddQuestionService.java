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

import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.shared.SaveResponseWithId;

/**
 * @author Joel Mumo
 * @date   15th June, 2020
 */
public interface AddQuestionService {

    AddQuestionInitialData getInitialData( Long sectionId );


    SaveResponse saveQuestion( AddQuestionRequest request );


    SaveResponseWithId saveComprehensionQuestion( AddComprehensionQuestionRequest request );
}
