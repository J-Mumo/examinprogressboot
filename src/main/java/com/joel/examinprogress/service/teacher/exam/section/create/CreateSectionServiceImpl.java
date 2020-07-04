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
package com.joel.examinprogress.service.teacher.exam.section.create;

import java.time.Duration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamTimerTypeEnum;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.service.shared.SaveResponseWithId;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@Service
public class CreateSectionServiceImpl implements CreateSectionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public CreateSectionInitialData getInitialData( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        boolean examTimedBySection = false;

        if ( ExamTimerTypeEnum.TIMED_PER_SECTION.getName().equals( exam.getExamTimerType()
                .getName() ) ) {
            examTimedBySection = Boolean.TRUE;
        }

        CreateSectionInitialData initialData = new CreateSectionInitialData( examTimedBySection );
        return initialData;
    }


    @Transactional
    @Override
    public SaveResponseWithId save( CreateSectionRequest request ) {

        Duration duration = null;
        if ( request.getDuration() != null ) {
            duration = Duration.parse( request.getDuration() );
        }

        Exam exam = examRepository.findById( request.getExamId() ).get();
        Section section = new Section();
        section.setName( request.getName() );
        section.setDescription( request.getDescription() );
        section.setDuration( duration );
        section.setExam( exam );
        sectionRepository.save( section );
        return new SaveResponseWithId( true, null, section.getId() );
    }
}
