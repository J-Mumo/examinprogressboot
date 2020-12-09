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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.service.teacher.exam.exams.ExamsInitialData;
import com.joel.examinprogress.service.teacher.rooms.RoomsService;

/**
 * @author Joel Mumo
 * @date   Dec 9, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/rooms" )
public class ExamRoomsController {

    @Autowired
    private RoomsService roomsService;

    @RequestMapping( value = "getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ExamsInitialData> getInitialData()
            throws IOException {

        ExamsInitialData initialData = roomsService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
