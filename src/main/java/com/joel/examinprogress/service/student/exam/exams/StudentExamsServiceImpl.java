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
package com.joel.examinprogress.service.student.exam.exams;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.student.ExamStatusRepository;
import com.joel.examinprogress.repository.student.StudentRepository;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
@Service
public class StudentExamsServiceImpl implements StudentExamsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private StudentExamTransferComparator examTransferComparator;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    private boolean checkIfExamHasStarted( Invite invite ) {

        boolean examHasStarted = true;
        LocalDate examStartDate = invite.getExamStartDate();
        LocalTime examStartTime = invite.getExamStartTime();
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if ( examStartTime != null && examStartTime.isAfter( now ) &&
                ( examStartDate.isEqual( today ) || examStartDate.isAfter( today ) ) ) {

            examHasStarted = false;
        }
        else if ( examStartTime == null && examStartDate.isAfter( today ) ) {

            examHasStarted = false;
        }

        return examHasStarted;
    }


    private boolean checkIfExamHasBeenCompletedByStudent( Exam exam, Student student ) {

        boolean examComplete = false;
        ExamStatus examStatus = examStatusRepository.findByExamAndStudent( exam, student );

        if ( examStatus != null && examStatus.getComplete() ) {

            examComplete = true;
        }

        return examComplete;
    }


    private StudentExamTransfer createExamTransfer( ExamToken examToken, Student student ) {

        Long examTokenId = examToken.getId();
        Invite invite = examToken.getInvite();
        String token = examToken.getToken();
        Exam exam = invite.getExam();
        boolean examHasStarted = checkIfExamHasStarted( invite );
        boolean examNotStarted = !examHasStarted;
        boolean viewResults = checkIfExamHasBeenCompletedByStudent( exam, student );
        boolean examinprogress = false;

        if ( examHasStarted && !viewResults )
            examinprogress = true;

        StudentExamTransfer transfer = new StudentExamTransfer( exam.getId(), exam.getName(), exam
                .getDescription(), examTokenId, examinprogress, examNotStarted, viewResults,
                token );

        return transfer;
    }


    private StudentExamTransfer[] createExamTransfers( Set<ExamToken> examTokens,
            Student student ) {

        SortedSet<StudentExamTransfer> examTransfers =
                new TreeSet<>( examTransferComparator );

        for ( ExamToken examToken : examTokens ) {

            examTransfers.add( createExamTransfer( examToken, student ) );
        }

        return examTransfers.toArray( new StudentExamTransfer[examTransfers.size()] );
    }


    @Override
    public StudentExamsInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Student student = studentRepository.findByUser( user );
        Set<ExamToken> examTokens = student.getExamTokens();
        StudentExamTransfer[] examTransfers = createExamTransfers( examTokens, student );
        StudentExamsInitialData initialData = new StudentExamsInitialData( examTransfers );
        return initialData;
    }

}
