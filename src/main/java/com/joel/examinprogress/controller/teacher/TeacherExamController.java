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
package com.joel.examinprogress.controller.teacher;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.ExamRequest;
import com.joel.examinprogress.service.teacher.exam.ExamService;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/exam" )
public class TeacherExamController {

    @Autowired
    ExamService examService;

    /**
     * 
     * @param examRequest
     * @return
     * @throws IOException
     */
    @RequestMapping( value = "save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody ExamRequest examRequest )
            throws IOException {

        SaveResponseWithId response = examService.save( examRequest );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }

}
