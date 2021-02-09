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
package com.joel.examinprogress.service.teacher.rooms;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.student.ExamStatus;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.student.ExamStatusRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.service.teacher.exam.exams.ExamTransfer;
import com.joel.examinprogress.service.teacher.exam.exams.ExamTransferComparator;
import com.joel.examinprogress.service.teacher.exam.exams.ExamsInitialData;

/**
 * @author Joel Mumo
 * @date   Dec 9, 2020
 */
@Service
public class RoomsServiceImpl implements RoomsService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private ExamTransferComparator examTransferComparator;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    private ExamTransfer createExamTransfer( Exam exam ) {

        ExamTransfer transfer = new ExamTransfer( exam.getId(), exam.getName(), exam
                .getDescription() );

        return transfer;
    }


    private ExamTransfer[] createExamTransfers( Set<Exam> exams ) {

        SortedSet<ExamTransfer> examTransfers =
                new TreeSet<>( examTransferComparator );

        for ( Exam exam : exams ) {

            examTransfers.add( createExamTransfer( exam ) );
        }

        return examTransfers.toArray( new ExamTransfer[examTransfers.size()] );
    }


    public boolean examInProgress( Invite invite ) {

        LocalDate today = LocalDate.now();
        LocalDate startDate = invite.getExamStartDate();
        LocalDate endDate = invite.getExamEndDate();
        LocalTime examStartTime = invite.getExamStartTime();
        LocalTime now = LocalTime.now();
        boolean examHasStarted = false;
        boolean examHasEnded = false;
        boolean examInProgress = false;

        if ( endDate != null && today.compareTo( endDate ) > 0 ) {
            examHasEnded = true;
        }
        else if ( endDate != null && today.compareTo( endDate ) == 0
                && examStartTime != null ) {

            if ( now.isAfter( examStartTime ) ) {
                examHasEnded = true;
            }
        }
        else if ( today.compareTo( startDate ) >= 0 ) {
            if ( examStartTime == null || now.isAfter( examStartTime ) ) {
                examHasStarted = true;
            }
        }

        if ( examHasStarted && !examHasEnded )
            examInProgress = true;

        return examInProgress;
    }


    @Override
    public ExamsInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        Set<Exam> exams = examRepository.findByTeacherId( teacher.getId() );
        Set<Exam> examsInProgress = new HashSet<>();

        for ( Exam exam : exams ) {

            Invite invite = exam.getInvite();

            if ( examInProgress( invite ) ) {
                examsInProgress.add( exam );
            }
        }

        ExamTransfer[] examTransfers = createExamTransfers( examsInProgress );
        ExamsInitialData initialData = new ExamsInitialData( examTransfers );
        return initialData;
    }


    @Override
    public Boolean terminateStudentExam( Long examTokenId ) {

        ExamToken examToken = examTokenRepository.findById( examTokenId ).get();
        Student student = examToken.getStudent();
        Exam exam = examToken.getInvite().getExam();
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );
        examStatus.setComplete( Boolean.TRUE );
        examStatusRepository.save( examStatus );

        return Boolean.TRUE;
    }
}
