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
package com.joel.examinprogress.service.teacher.exam.invite.view;

import java.time.LocalTime;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.link.LinkHelper;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.student.ExamStatusRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.service.email.EmailService;
import com.joel.examinprogress.service.shared.DeleteResponse;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
@Service
public class ViewInviteServiceImpl implements ViewInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private LoggedInCredentialsHelper credentialsHelper;

    @Autowired
    private ExamTokenTransferComparator examTokenTransferComparator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LinkHelper linkHelper;

    private ExamTokenTransfer createSectionTransfer( ExamToken examToken ) {

        ExamTokenTransfer transfer = new ExamTokenTransfer(
                examToken.getId(),
                examToken.getEmail() );

        return transfer;
    }


    private ExamTokenTransfer[] createExamTokenTransfers( Set<ExamToken> examTokens ) {

        SortedSet<ExamTokenTransfer> examTokenTransfers =
                new TreeSet<>( examTokenTransferComparator );

        for ( ExamToken examToken : examTokens ) {

            examTokenTransfers.add( createSectionTransfer( examToken ) );
        }

        return examTokenTransfers.toArray( new ExamTokenTransfer[examTokenTransfers.size()] );
    }


    private DeleteResponse updateTokensAndDeleteToken( ExamToken examToken ) {

        User user = credentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        teacher.setTokens( teacher.getTokens() + 1 );
        teacherRepository.save( teacher );
        examTokenRepository.delete( examToken );
        return new DeleteResponse( true, null );
    }


    @Override
    public ViewInviteInitialData getInitialData( Long inviteId, String domain, Integer serverPort,
            String protocol ) {

        Invite invite = inviteRepository.findById( inviteId ).get();
        String inviteCode = linkHelper.createDomainLink( domain, serverPort, protocol ) +
                "/student/exam/detail?invitecode=" + invite.getInviteCode();

        LocalTime startTime = invite.getExamStartTime();
        String inviteStartTime = startTime != null ? startTime.toString() : null;
        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );
        ExamTokenTransfer[] examTokenTransfers = createExamTokenTransfers( examTokens );

        ViewInviteInitialData initialData = new ViewInviteInitialData(
                invite.getName(), invite.getExamStartDate(), invite.getExamEndDate(),
                invite.getPausable(), inviteStartTime, inviteCode, examTokenTransfers );

        return initialData;
    }


    @Transactional
    @Override
    public DeleteResponse unsendInvite( Long examTokenId ) {

        DeleteResponse response = new DeleteResponse();
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        Student student = examToken.getStudent();
        Exam exam = examToken.getInvite().getExam();

        // Check if student has started exam
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );

        if ( examStatus == null || !examStatus.getStarted() ) {

            response = updateTokensAndDeleteToken( examToken );
        }
        else {

            response = new DeleteResponse( false, "Cannot unsend this invite, the exam token has"
                    + "been consumed" );
        }

        return response;
    }


    @Transactional
    @Override
    public SaveResponse resendInvite( Long examTokenId, String domain, Integer serverPort,
            String protocol ) {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        String token = examToken.getToken();
        String email = examToken.getEmail();
        Invite invite = examToken.getInvite();
        Locale locale = new Locale( "en" );

        EmailSentResponse emailSentResponse =
                emailService.sendInviteToExam( organisation, email, token, invite, locale,
                        domain, serverPort, protocol );

        if ( !emailSentResponse.isEmailSent() ) {

            return new SaveResponse( false, "Problem sending email" );
        }

        return new SaveResponse( true, null );
    }
}
