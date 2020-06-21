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
import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.create.CreateExamRequest;
import com.joel.examinprogress.service.teacher.exam.create.CreateExamService;
import com.joel.examinprogress.service.teacher.exam.edit.EditExamInitialData;
import com.joel.examinprogress.service.teacher.exam.edit.EditExamService;
import com.joel.examinprogress.service.teacher.exam.exams.ExamsInitialData;
import com.joel.examinprogress.service.teacher.exam.exams.ExamsService;
import com.joel.examinprogress.service.teacher.exam.section.create.CreateSectionRequest;
import com.joel.examinprogress.service.teacher.exam.section.create.CreateSectionService;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionRequest;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionService;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.add.AddMultipleChoiceQuestionRequest;
import com.joel.examinprogress.service.teacher.exam.section.question.multiplechoice.add.AddMultipleChoiceQuestionService;
import com.joel.examinprogress.service.teacher.exam.section.sections.SectionsInitialData;
import com.joel.examinprogress.service.teacher.exam.section.sections.SectionsService;
import com.joel.examinprogress.service.teacher.exam.section.view.ViewSectionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.view.ViewSectionService;
import com.joel.examinprogress.service.teacher.exam.view.ViewExamInitialData;
import com.joel.examinprogress.service.teacher.exam.view.ViewExamService;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/exam" )
public class TeacherExamController {

    @Autowired
    ExamsService examsService;

    @Autowired
    CreateExamService examService;

    @Autowired
    EditExamService editExamService;

    @Autowired
    ViewExamService viewExamService;

    @Autowired
    CreateSectionService createSectionService;

    @Autowired
    EditSectionService editSectionService;

    @Autowired
    ViewSectionService viewSectionService;

    @Autowired
    SectionsService sectionsService;

    @Autowired
    AddMultipleChoiceQuestionService addMultipleChoiceQuestionService;

    @RequestMapping( value = "exams/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ExamsInitialData> getInitialData()
            throws IOException {

        ExamsInitialData initialData = examsService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "edit/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<EditExamInitialData> getEditExamInitialData( @RequestBody Long examId )
            throws IOException {

        EditExamInitialData initialData = editExamService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewExamInitialData> getInitial( @RequestBody Long examId )
            throws IOException {

        ViewExamInitialData initialData = viewExamService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody CreateExamRequest examRequest )
            throws IOException {

        SaveResponseWithId response = examService.save( examRequest );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewSectionInitialData> getViewSectionInitialData(
            @RequestBody Long sectionId )
            throws IOException {

        ViewSectionInitialData initialData = viewSectionService.getInitialData( sectionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/create/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody CreateSectionRequest sectionRequest )
            throws IOException {

        SaveResponseWithId response = createSectionService.save( sectionRequest );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/edit/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<EditSectionInitialData> getSectionInitialData(
            @RequestBody Long sectionId )
            throws IOException {

        EditSectionInitialData initialData = editSectionService.getInitialData( sectionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/edit/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody EditSectionRequest request )
            throws IOException {

        SaveResponse response = editSectionService.save( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/multiplechoicequestion/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody AddMultipleChoiceQuestionRequest request )
            throws IOException {

        SaveResponse response = addMultipleChoiceQuestionService.save( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "sections/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<SectionsInitialData> getInitialData(
            @RequestBody Long examId )
            throws IOException {

        SectionsInitialData initialData = sectionsService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
