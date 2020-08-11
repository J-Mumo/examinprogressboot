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
public class StudentExamsServiceImpl implements StudentExamsService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentExamTransferComparator examTransferComparator;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    private StudentExamTransfer createExamTransfer( Exam exam ) {

        StudentExamTransfer transfer = new StudentExamTransfer( exam.getId(), exam.getName(), exam
                .getDescription() );

        return transfer;
    }


    private StudentExamTransfer[] createExamTransfers( Set<Exam> exams ) {

        SortedSet<StudentExamTransfer> examTransfers =
                new TreeSet<>( examTransferComparator );

        for ( Exam exam : exams ) {

            examTransfers.add( createExamTransfer( exam ) );
        }

        return examTransfers.toArray( new StudentExamTransfer[examTransfers.size()] );
    }


    @Override
    public StudentExamsInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        Set<Exam> exams = examRepository.findByTeacherId( teacher.getId() );
        StudentExamTransfer[] examTransfers = createExamTransfers( exams );
        StudentExamsInitialData initialData = new StudentExamsInitialData( examTransfers );
        return initialData;
    }

}
