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
package com.joel.examinprogress.service.teacher.exam.create;

import java.time.Duration;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerType;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.ExamTimerTypeRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;
import com.joel.examinprogress.service.teacher.exam.shared.ExamTimerTypeTransfer;
import com.joel.examinprogress.service.teacher.exam.shared.ExamTimerTypeTransferComparator;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@Service
public class CreateExamServiceImpl implements CreateExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ExamTimerTypeRepository examTimerTypeRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ExamTimerTypeTransferComparator examTimerTypeTransferComparator;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    private ExamTimerTypeTransfer createExamTimerTypeTransfer( ExamTimerType examTimerType ) {

        ExamTimerTypeTransfer transfer = new ExamTimerTypeTransfer(
                examTimerType.getId(),
                examTimerType.getName() );

        return transfer;
    }


    private ExamTimerTypeTransfer[] createExamTimerTypeTransfers( List<
            ExamTimerType> examTimerTypes ) {

        SortedSet<ExamTimerTypeTransfer> examTimerTypeTransfers =
                new TreeSet<>( examTimerTypeTransferComparator );

        for ( ExamTimerType examTimerType : examTimerTypes ) {

            examTimerTypeTransfers.add( createExamTimerTypeTransfer( examTimerType ) );
        }

        return examTimerTypeTransfers.toArray( new ExamTimerTypeTransfer[examTimerTypeTransfers
                .size()] );
    }


    @Override
    public CreateExamInitialData getInitialData() {

        List<ExamTimerType> examTimerTypes = examTimerTypeRepository.findAll();
        ExamTimerTypeTransfer[] examTimerTypeTransfers = createExamTimerTypeTransfers(
                examTimerTypes );

        CreateExamInitialData initialData = new CreateExamInitialData( examTimerTypeTransfers );
        return initialData;
    }


    @Transactional
    @Override
    public SaveResponseWithId save( CreateExamRequest request ) {

        Duration duration = null;
        if ( request.getDuration() != null ) {
            duration = Duration.parse( request.getDuration() );
        }
        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        ExamTimerType examTimerType = examTimerTypeRepository.findById( request
                .getExamTimerTypeId() ).get();

        Exam exam = new Exam();
        exam.setName( request.getName() );
        exam.setDescription( request.getDescription() );
        exam.setDuration( duration );
        exam.setTeacher( teacher );
        exam.setExamTimerType( examTimerType );
        examRepository.save( exam );
        return new SaveResponseWithId( true, null, exam.getId() );
    }

}
