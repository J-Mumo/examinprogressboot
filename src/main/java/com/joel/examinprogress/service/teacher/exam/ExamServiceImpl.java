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
package com.joel.examinprogress.service.teacher.exam;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamRepository examRepository;

    @Transactional
    @Override
    public SaveResponseWithId save( ExamRequest request ) {

        Exam exam = new Exam();
        exam.setName( request.getName() );
        exam.setDescription( request.getDescription() );
        exam.setStartTime( request.getStartTime() );
        exam.setDuration( request.getDuration() );
        exam.setComplete( Boolean.FALSE );
        examRepository.save( exam );
        return new SaveResponseWithId( true, null, exam.getId() );
    }

}
