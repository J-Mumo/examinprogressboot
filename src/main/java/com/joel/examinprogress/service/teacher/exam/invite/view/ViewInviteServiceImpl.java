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
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.helper.link.LinkHelper;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
import com.joel.examinprogress.service.shared.DeleteResponse;

/**
 * @author Joel Mumo
 * @date   28th July, 2020
 */
@Service
public class ViewInviteServiceImpl implements ViewInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamTokenTransferComparator examTokenTransferComparator;

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

        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        Student student = examToken.getStudent();
        student.setExamToken( null );
        studentRepository.save( student );
        examTokenRepository.delete( examToken );

        return new DeleteResponse( true, null );
    }
}
