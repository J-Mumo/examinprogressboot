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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@Service
public class CreateExamServiceImpl implements CreateExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @Transactional
    @Override
    public SaveResponseWithId save( CreateExamRequest request ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );
        LocalDateTime examStartTime = LocalDateTime.parse( request.getStartTime(), formatter );
        Duration examTime = Duration.parse( request.getDuration() );
        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );

        Exam exam = new Exam();
        exam.setName( request.getName() );
        exam.setDescription( request.getDescription() );
        exam.setStartTime( examStartTime );
        exam.setDuration( examTime );
        exam.setComplete( Boolean.FALSE );
        exam.setTeacher( teacher );
        examRepository.save( exam );
        return new SaveResponseWithId( true, null, exam.getId() );
    }

}
