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
package com.joel.examinprogress.service.teacher.exam.exams;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
@Service
public class ExamsServiceImpl implements ExamsService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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


    @Override
    public ExamsInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        Set<Exam> exams = examRepository.findByTeacherId( teacher.getId() );
        ExamTransfer[] examTransfers = createExamTransfers( exams );
        ExamsInitialData initialData = new ExamsInitialData( examTransfers );
        return initialData;
    }

}
