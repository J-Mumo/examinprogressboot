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
package com.joel.examinprogress.service.student.exam.detail;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerTypeEnum;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.student.StudentRepository;

/**
 * @author Joel Mumo
 * @date   10th Aug, 2020
 */
@Service
public class ExamDetailServiceImpl implements ExamDetailService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ExamDetailInitialData getInitialData( ExamDetailRequest request ) {

        boolean examExists = false;
        boolean studentRegistered = false;
        String email = null;
        Exam exam = null;
        Invite invite = null;
        boolean examHasStarted = false;
        boolean examHasEnded = false;
        Date startDate = null;
        Date endDate = null;
        boolean timedPerExam = false;
        boolean timedPerSection = false;
        boolean timedPerQuestion = false;

        if ( request.isInviteCode() ) {
            invite = inviteRepository.findByInviteCode( request.getCode() );
            if ( invite != null ) {
                examExists = true;
                exam = invite.getExam();
            }
        }
        else {
            ExamToken examToken = examTokenRepository.findByToken( request.getCode() );
            if ( examToken != null ) {
                examExists = true;
                email = examToken.getEmail();
                invite = examToken.getInvite();
                exam = invite.getExam();

                if ( studentRepository.findByExamToken( examToken ) != null )
                    studentRegistered = true;
            }
        }

        if ( exam != null ) {
            Date today = new Date();
            startDate = invite.getExamStartDate();
            endDate = invite.getExamEndDate();
            LocalTime examStartTime = invite.getExamStartTime();
            LocalTime now = LocalTime.now();

            if ( endDate != null && today.compareTo( endDate ) > 0 ) {
                examHasEnded = true;
            }
            else if ( endDate != null && today.compareTo( endDate ) == 0 ) {
                if ( now.isAfter( examStartTime ) ) {
                    examHasEnded = true;
                }
            }
            else if ( today.compareTo( startDate ) <= 0 ) {
                if ( examStartTime == null || now.isAfter( examStartTime ) ) {
                    examHasStarted = true;
                }
            }

            if ( exam.getExamTimerType().getId() == ExamTimerTypeEnum.TIMED_PER_EXAM
                    .getExamTimerTypeId() )
                timedPerExam = true;

            else if ( exam.getExamTimerType().getId() == ExamTimerTypeEnum.TIMED_PER_SECTION
                    .getExamTimerTypeId() )
                timedPerSection = true;
            else
                timedPerQuestion = true;
        }

        ExamDetailInitialData initialData = new ExamDetailInitialData( examExists,
                studentRegistered, examHasStarted, examHasEnded, exam.getId(), exam.getName(),
                exam.getDescription(), invite.getExamStartDate(), invite.getExamEndDate(),
                invite.getExamStartTime(), exam.getTotalExamTime().toMinutes(),
                invite.getPausable(), timedPerExam, timedPerSection, timedPerQuestion, email );

        return initialData;
    }

}
