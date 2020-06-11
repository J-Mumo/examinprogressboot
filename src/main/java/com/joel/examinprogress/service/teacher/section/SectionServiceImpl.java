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
package com.joel.examinprogress.service.teacher.section;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Transactional
    @Override
    public SaveResponse save( SectionRequest request ) {

        Exam exam = examRepository.findById( request.getExamId() ).get();
        Section section = new Section();
        section.setName( request.getName() );
        section.setExam( exam );
        sectionRepository.save( section );
        return new SaveResponse( true, null );
    }

}
