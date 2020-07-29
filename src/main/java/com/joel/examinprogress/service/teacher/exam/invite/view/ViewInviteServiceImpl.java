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

import java.time.Duration;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
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
    private ExamTokenTransferComparator examTokenTransferComparator;

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
    public ViewInviteInitialData getInitialData( Long inviteId ) {

        Invite invite = inviteRepository.findById( inviteId ).get();
        String examLink = invite.getExam().getExamLink();
        Duration inviteStartTime = invite.getExamStartTime();
        String startTime = inviteStartTime != null ? String.format( "%d:%02d:%02d", inviteStartTime
                .getSeconds() / 3600, ( inviteStartTime.getSeconds() % 3600 ) / 60,
                ( inviteStartTime
                        .getSeconds() % 60 ) ) : null;

        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );
        ExamTokenTransfer[] examTokenTransfers = createExamTokenTransfers( examTokens );

        ViewInviteInitialData initialData = new ViewInviteInitialData(
                invite.getName(), invite.getExamStartDate(), invite.getExamEndDate(),
                invite.getPausable(), startTime, examLink, examTokenTransfers );

        return initialData;
    }


    @Transactional
    @Override
    public DeleteResponse unsendInvite( Long examTokenId ) {

        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        examTokenRepository.delete( examToken );

        return new DeleteResponse( true, null );
    }

}
