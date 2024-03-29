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

import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.teacher.results.examresults.ResultsInitialData;
import com.joel.examinprogress.service.teacher.results.examresults.ResultsService;
import com.joel.examinprogress.service.teacher.results.finalizescoring.FinalizeScoringInitialData;
import com.joel.examinprogress.service.teacher.results.finalizescoring.FinalizeScoringRequest;
import com.joel.examinprogress.service.teacher.results.finalizescoring.FinalizeScoringRequestInitialData;
import com.joel.examinprogress.service.teacher.results.finalizescoring.FinalizeScoringService;
import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceInitialData;
import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceRequestInitialData;
import com.joel.examinprogress.service.teacher.results.sectionperformance.SectionPerformanceService;
import com.joel.examinprogress.service.teacher.results.showexams.ShowExamsInitialData;
import com.joel.examinprogress.service.teacher.results.showexams.ShowExamsService;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceInitialData;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceRequestInitialData;
import com.joel.examinprogress.service.teacher.results.viewperformance.ViewPerformanceService;

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

    @Autowired
    private FinalizeScoringService finalizeScoringService;

    @Autowired
    private ViewPerformanceService viewPerformanceService;

    @Autowired
    private SectionPerformanceService sectionPerformanceService;

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


    @RequestMapping( value = "examresults/finalizescoring", method = RequestMethod.POST )
    public ResponseEntity<FinalizeScoringInitialData> getInitialData(
            @RequestBody FinalizeScoringRequestInitialData request ) throws IOException {

        FinalizeScoringInitialData initialData = finalizeScoringService.getInitialData( request );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "examresults/finalizescore", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody FinalizeScoringRequest request ) throws IOException {

        SaveResponse response = finalizeScoringService.save( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "examresults/viewperformance", method = RequestMethod.POST )
    public ResponseEntity<ViewPerformanceInitialData> getInitialData(
            @RequestBody ViewPerformanceRequestInitialData request ) throws IOException {

        ViewPerformanceInitialData initialData = viewPerformanceService.getInitialData( request );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "examresults/sectionperformance", method = RequestMethod.POST )
    public ResponseEntity<SectionPerformanceInitialData> getInitialData(
            @RequestBody SectionPerformanceRequestInitialData request ) throws IOException {

        SectionPerformanceInitialData initialData = sectionPerformanceService.getInitialData(
                request );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
