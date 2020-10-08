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
package com.joel.examinprogress.service.teacher.results.showexams;

import java.util.HashSet;
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
import com.joel.examinprogress.service.teacher.exam.exams.ExamTransfer;
import com.joel.examinprogress.service.teacher.exam.exams.ExamTransferComparator;

/**
 * @author Joel Mumo
 * @date   Oct 8, 2020
 */
@Service
public class ShowExamsServiceImpl implements ShowExamsService {

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
    public ShowExamsInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        Set<Exam> exams = examRepository.findByTeacherId( teacher.getId() );
        Set<Exam> examsWithResults = new HashSet<>();

        for ( Exam exam : exams ) {

            if ( !exam.getResults().isEmpty() ) {

                examsWithResults.add( exam );
            }
        }

        ExamTransfer[] examTransfers = createExamTransfers( examsWithResults );
        ShowExamsInitialData initialData = new ShowExamsInitialData( examTransfers );
        return initialData;
    }

}
