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

import com.joel.examinprogress.service.student.exam.detail.ExamDetailInitialData;
import com.joel.examinprogress.service.student.exam.detail.ExamDetailRequest;
import com.joel.examinprogress.service.student.exam.detail.ExamDetailService;
import com.joel.examinprogress.service.student.exam.examinprogress.AnswerRequest;
import com.joel.examinprogress.service.student.exam.examinprogress.ExaminprogressResponse;
import com.joel.examinprogress.service.student.exam.examinprogress.ExaminprogressService;
import com.joel.examinprogress.service.student.exam.examinprogress.SkipQuestionRequest;
import com.joel.examinprogress.service.student.exam.examinprogress.SkipSectionRequest;
import com.joel.examinprogress.service.student.exam.exams.StudentExamsInitialData;
import com.joel.examinprogress.service.student.exam.exams.StudentExamsService;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
@RestController
@RequestMapping( "/examinprogress/student/exam" )
public class StudentExamController {

    @Autowired
    private ExamDetailService examDetailService;

    @Autowired
    private ExaminprogressService examinprogressService;

    @Autowired
    private StudentExamsService studentExamsService;

    @RequestMapping( value = "exams/initialdata", method = RequestMethod.POST )
    public ResponseEntity<StudentExamsInitialData> getInitialData()
            throws IOException {

        StudentExamsInitialData initialData = studentExamsService.getInitialData();

        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "detail/initialdata", method = RequestMethod.POST )
    public ResponseEntity<ExamDetailInitialData> getInitialData(
            @RequestBody ExamDetailRequest request )
            throws IOException {

        ExamDetailInitialData initialData = examDetailService.getInitialData( request );

        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "examinprogress/getexamprogress", method = RequestMethod.POST )
    public ResponseEntity<ExaminprogressResponse> getExamProgress(
            @RequestBody Long examTokenId )
            throws IOException {

        ExaminprogressResponse response = examinprogressService.getExamProgress( examTokenId );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "examinprogress/save/answer", method = RequestMethod.POST )
    public ResponseEntity<ExaminprogressResponse> saveAnswer(
            @RequestBody AnswerRequest request )
            throws IOException {

        ExaminprogressResponse response = examinprogressService.saveAnswer( request );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "examinprogress/skipquestion", method = RequestMethod.POST )
    public ResponseEntity<ExaminprogressResponse> skipQuestion(
            @RequestBody SkipQuestionRequest request )
            throws IOException {

        ExaminprogressResponse response = examinprogressService.skipQuestion( request );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "examinprogress/skipsection", method = RequestMethod.POST )
    public ResponseEntity<ExaminprogressResponse> skipSection(
            @RequestBody SkipSectionRequest request )
            throws IOException {

        ExaminprogressResponse response = examinprogressService.skipSection( request );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "examinprogress/terminate", method = RequestMethod.POST )
    public ResponseEntity<ExaminprogressResponse> terminateExam(
            @RequestBody Long examTokenId )
            throws IOException {

        ExaminprogressResponse response = examinprogressService.terminateExam( examTokenId );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }
}
