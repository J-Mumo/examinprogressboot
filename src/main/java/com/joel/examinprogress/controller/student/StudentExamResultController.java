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
package com.joel.examinprogress.controller.student;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.service.student.exam.result.StudentResultService;
import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceInitialData;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceInitialData;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
@RestController
@RequestMapping( "/examinprogress/student/exam/result" )
public class StudentExamResultController {

    @Autowired
    private StudentResultService studentResultService;

    @RequestMapping( value = "", method = RequestMethod.POST )
    public ResponseEntity<ViewPerformanceInitialData> getInitialData(
            @RequestBody Long examTokenId ) throws IOException {

        ViewPerformanceInitialData initialData = studentResultService.getInitialData( examTokenId );

        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "sectionresult", method = RequestMethod.POST )
    public ResponseEntity<SectionPerformanceInitialData> getSectionResult(
            @RequestBody Long sectionId ) throws IOException {

        SectionPerformanceInitialData initialData = studentResultService.getSectionResult(
                sectionId );

        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
