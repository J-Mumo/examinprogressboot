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
package com.joel.examinprogress.service.teacher.rooms;

import com.joel.examinprogress.service.teacher.exam.exams.ExamsInitialData;

/**
 * @author Joel Mumo
 * @date   Dec 9, 2020
 */
public interface RoomsService {

    ExamsInitialData getInitialData();


    Boolean terminateStudentExam( Long examTokenId );
}
