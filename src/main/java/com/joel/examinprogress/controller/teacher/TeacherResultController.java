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

import com.joel.examinprogress.service.teacher.results.examresults.ResultsInitialData;
import com.joel.examinprogress.service.teacher.results.examresults.ResultsService;
import com.joel.examinprogress.service.teacher.results.showexams.ShowExamsInitialData;
import com.joel.examinprogress.service.teacher.results.showexams.ShowExamsService;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/results" )
public class TeacherResultController {

    @Autowired
    private ShowExamsService showExamsService;

    @Autowired
    private ResultsService resultsService;

    @RequestMapping( value = "exams/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ShowExamsInitialData>
            getInitialData()
            throws IOException {

        ShowExamsInitialData initialData = showExamsService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "examresults/initialdata", method = RequestMethod.POST )
    public ResponseEntity<ResultsInitialData> getInitialData( @RequestBody Long examId )
            throws IOException {

        ResultsInitialData initialData = resultsService.getResultsInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
