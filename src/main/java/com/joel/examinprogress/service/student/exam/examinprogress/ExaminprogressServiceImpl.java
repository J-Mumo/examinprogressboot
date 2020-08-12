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
package com.joel.examinprogress.service.student.exam.examinprogress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.repository.exam.ExamRepository;

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
@Service
public class ExaminprogressServiceImpl implements ExaminprogressService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public ExaminprogressResponse getExamProgress( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        return null;
    }
}
