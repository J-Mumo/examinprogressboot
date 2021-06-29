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

import com.joel.examinprogress.context.threads.ThreadLocals;
import com.joel.examinprogress.service.shared.DeleteResponse;
import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.create.CreateExamInitialData;
import com.joel.examinprogress.service.teacher.exam.create.CreateExamRequest;
import com.joel.examinprogress.service.teacher.exam.create.CreateExamService;
import com.joel.examinprogress.service.teacher.exam.delete.DeleteExamService;
import com.joel.examinprogress.service.teacher.exam.edit.EditExamInitialData;
import com.joel.examinprogress.service.teacher.exam.edit.EditExamRequest;
import com.joel.examinprogress.service.teacher.exam.edit.EditExamService;
import com.joel.examinprogress.service.teacher.exam.exams.ExamsInitialData;
import com.joel.examinprogress.service.teacher.exam.exams.ExamsService;
import com.joel.examinprogress.service.teacher.exam.invite.create.CreateInviteInitialData;
import com.joel.examinprogress.service.teacher.exam.invite.create.CreateInviteRequest;
import com.joel.examinprogress.service.teacher.exam.invite.create.CreateInviteService;
import com.joel.examinprogress.service.teacher.exam.invite.delete.DeleteInviteService;
import com.joel.examinprogress.service.teacher.exam.invite.edit.EditInviteInitialData;
import com.joel.examinprogress.service.teacher.exam.invite.edit.EditInviteRequest;
import com.joel.examinprogress.service.teacher.exam.invite.edit.EditInviteService;
import com.joel.examinprogress.service.teacher.exam.invite.invites.InvitesInitialData;
import com.joel.examinprogress.service.teacher.exam.invite.invites.InvitesService;
import com.joel.examinprogress.service.teacher.exam.invite.send.SendInviteInitialData;
import com.joel.examinprogress.service.teacher.exam.invite.send.SendInviteRequest;
import com.joel.examinprogress.service.teacher.exam.invite.send.SendInviteResponse;
import com.joel.examinprogress.service.teacher.exam.invite.send.SendInviteService;
import com.joel.examinprogress.service.teacher.exam.invite.send.SendInviteToEmailRequest;
import com.joel.examinprogress.service.teacher.exam.invite.view.ViewInviteInitialData;
import com.joel.examinprogress.service.teacher.exam.invite.view.ViewInviteService;
import com.joel.examinprogress.service.teacher.exam.section.create.CreateSectionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.create.CreateSectionRequest;
import com.joel.examinprogress.service.teacher.exam.section.create.CreateSectionService;
import com.joel.examinprogress.service.teacher.exam.section.delete.DeleteSectionService;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionRequest;
import com.joel.examinprogress.service.teacher.exam.section.edit.EditSectionService;
import com.joel.examinprogress.service.teacher.exam.section.question.add.AddComprehensionQuestionRequest;
import com.joel.examinprogress.service.teacher.exam.section.question.add.AddQuestionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.question.add.AddQuestionRequest;
import com.joel.examinprogress.service.teacher.exam.section.question.add.AddQuestionService;
import com.joel.examinprogress.service.teacher.exam.section.question.delete.DeleteQuestionService;
import com.joel.examinprogress.service.teacher.exam.section.question.edit.EditQuestionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.question.edit.EditQuestionRequest;
import com.joel.examinprogress.service.teacher.exam.section.question.edit.EditQuestionService;
import com.joel.examinprogress.service.teacher.exam.section.question.view.ViewQuestionInitialData;
import com.joel.examinprogress.service.teacher.exam.section.question.view.ViewQuestionService;
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
    private ExamsService examsService;

    @Autowired
    private CreateExamService createExamService;

    @Autowired
    private EditExamService editExamService;

    @Autowired
    private ViewExamService viewExamService;

    @Autowired
    private DeleteExamService deleteExamService;

    @Autowired
    private CreateSectionService createSectionService;

    @Autowired
    private EditSectionService editSectionService;

    @Autowired
    private ViewSectionService viewSectionService;

    @Autowired
    private DeleteSectionService deleteSectionService;

    @Autowired
    private SectionsService sectionsService;

    @Autowired
    private AddQuestionService addQuestionService;

    @Autowired
    private ViewQuestionService viewQuestionService;

    @Autowired
    private EditQuestionService editQuestionService;

    @Autowired
    private DeleteQuestionService deleteQuestionService;

    @Autowired
    private CreateInviteService createInviteService;

    @Autowired
    private SendInviteService sendInviteService;

    @Autowired
    private ViewInviteService viewInviteService;

    @Autowired
    private EditInviteService editInviteService;

    @Autowired
    private InvitesService invitesService;

    @Autowired
    private DeleteInviteService deleteInviteService;

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


    @RequestMapping( value = "edit/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody EditExamRequest examRequest )
            throws IOException {

        SaveResponse response = editExamService.save( examRequest );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewExamInitialData> getInitial( @RequestBody Long examId )
            throws IOException {

        ViewExamInitialData initialData = viewExamService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "create/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<CreateExamInitialData> getCreateExamInitialData()
            throws IOException {

        CreateExamInitialData initialData = createExamService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody CreateExamRequest examRequest )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        SaveResponseWithId response = createExamService.save( examRequest, domain );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "delete", method = RequestMethod.POST )
    public ResponseEntity<DeleteResponse> deleteExam(
            @RequestBody Long examId )
            throws IOException {

        DeleteResponse response = deleteExamService.deleteExam( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewSectionInitialData> getViewSectionInitialData(
            @RequestBody Long sectionId )
            throws IOException {

        ViewSectionInitialData initialData = viewSectionService.getInitialData( sectionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/create/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<CreateSectionInitialData> getCreateSectionInitialData(
            @RequestBody Long examId )
            throws IOException {

        CreateSectionInitialData initialData = createSectionService.getInitialData( examId );
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


    @RequestMapping( value = "section/delete", method = RequestMethod.POST )
    public ResponseEntity<DeleteResponse> deleteSection(
            @RequestBody Long sectionId )
            throws IOException {

        DeleteResponse response = deleteSectionService.deleteSection( sectionId );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "sections/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<SectionsInitialData> getInitialData(
            @RequestBody Long examId )
            throws IOException {

        SectionsInitialData initialData = sectionsService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/question/add/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<AddQuestionInitialData> getAddQuestionInitialData(
            @RequestBody Long sectionId )
            throws IOException {

        AddQuestionInitialData initialData = addQuestionService.getInitialData( sectionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/question/add/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody AddQuestionRequest request )
            throws IOException {

        SaveResponse response = addQuestionService.saveQuestion( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/comprehensionquestion/add/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody AddComprehensionQuestionRequest request )
            throws IOException {

        SaveResponseWithId response = addQuestionService.saveComprehensionQuestion( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/question/view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewQuestionInitialData> getViewQuestionInitialData(
            @RequestBody Long questionId )
            throws IOException {

        ViewQuestionInitialData initialData = viewQuestionService.getInitialData( questionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/question/edit/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<EditQuestionInitialData> getEditQuestionInitialData(
            @RequestBody Long questionId )
            throws IOException {

        EditQuestionInitialData initialData = editQuestionService.getInitialData( questionId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "section/question/edit/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody EditQuestionRequest request )
            throws IOException {

        SaveResponseWithId response = editQuestionService.saveQuestion( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "section/question/delete", method = RequestMethod.POST )
    public ResponseEntity<DeleteResponse> deleteQuestion(
            @RequestBody Long questionId )
            throws IOException {

        DeleteResponse response = deleteQuestionService.deleteQuestion( questionId );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/create/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<CreateInviteInitialData> getCreateInviteInitialData(
            @RequestBody Long examId ) throws IOException {

        CreateInviteInitialData initialData = createInviteService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "invite/create/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponseWithId> save(
            @RequestBody CreateInviteRequest request )
            throws IOException {

        SaveResponseWithId response = createInviteService.save( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/send/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<SendInviteInitialData> getSendInviteInitialData(
            @RequestBody Long inviteId )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        SendInviteInitialData examLink = sendInviteService.getInitialData( inviteId, domain,
                serverPort, protocol );

        return ResponseEntity.status( HttpStatus.OK ).body( examLink );
    }


    @RequestMapping( value = "invite/sendtoemail", method = RequestMethod.POST )
    public ResponseEntity<SendInviteResponse> sendInviteToEmail(
            @RequestBody SendInviteToEmailRequest request )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        SendInviteResponse response = sendInviteService.sendInviteToEmail( request, domain,
                serverPort, protocol );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/send", method = RequestMethod.POST )
    public ResponseEntity<SendInviteResponse> sendInvite(
            @RequestBody SendInviteRequest request )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        SendInviteResponse response = sendInviteService.sendInvite( request, domain, serverPort,
                protocol );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/view/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<ViewInviteInitialData> getViewInviteInitialData(
            @RequestBody Long inviteId )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        ViewInviteInitialData examLink = viewInviteService.getInitialData( inviteId, domain,
                serverPort, protocol );
        return ResponseEntity.status( HttpStatus.OK ).body( examLink );
    }


    @RequestMapping( value = "invite/unsendtoemail", method = RequestMethod.POST )
    public ResponseEntity<DeleteResponse> unsendInvite(
            @RequestBody Long examTokenId ) throws IOException {

        DeleteResponse response = viewInviteService.unsendInvite( examTokenId );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/resend", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> resendInvite(
            @RequestBody Long examTokenId ) throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();

        SaveResponse response = viewInviteService.resendInvite( examTokenId, domain, serverPort,
                protocol );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/edit/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<EditInviteInitialData> getEditInviteInitialData(
            @RequestBody Long inviteId )
            throws IOException {

        EditInviteInitialData initialData = editInviteService.getInitialData( inviteId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "invite/edit/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody EditInviteRequest request )
            throws IOException {

        SaveResponse response = editInviteService.save( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "invite/invites/getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<InvitesInitialData> getInvitesInitialData(
            @RequestBody Long examId )
            throws IOException {

        InvitesInitialData initialData = invitesService.getInitialData( examId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "invite/delete", method = RequestMethod.POST )
    public ResponseEntity<DeleteResponse> deleteInvite(
            @RequestBody Long inviteId )
            throws IOException {

        DeleteResponse response = deleteInviteService.deleteInvite( inviteId );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }
}
