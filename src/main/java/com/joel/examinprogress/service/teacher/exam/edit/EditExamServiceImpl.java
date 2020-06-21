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
package com.joel.examinprogress.service.teacher.exam.edit;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.repository.exam.ExamRepository;

/**
 * @author Joel Mumo
 * @date   21st June, 2020
 */
public class EditExamServiceImpl implements EditExamService {

    @Autowired
    ExamRepository examRepository;

    @Override
    public EditExamInitialData getInitialData( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        Duration examDuration = exam.getDuration();
        String duration = String.format( "%d:%02d:%02d", examDuration.getSeconds() / 3600,
                ( examDuration.getSeconds() % 3600 ) / 60, ( examDuration.getSeconds() % 60 ) );

        EditExamInitialData initialData = new EditExamInitialData(
                exam.getName(), exam.getDescription(), exam.getStartTime(),
                duration, exam.isComplete() );

        return initialData;
    }

}
