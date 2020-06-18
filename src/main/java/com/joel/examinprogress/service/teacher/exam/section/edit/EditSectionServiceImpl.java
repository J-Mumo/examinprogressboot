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
package com.joel.examinprogress.service.teacher.exam.section.edit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   17th June, 2020
 */
@Service
public class EditSectionServiceImpl implements EditSectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public EditSectionInitialData getInitialData( Long sectionId ) {

        Section section = sectionRepository.findById( sectionId ).get();
        EditSectionInitialData initialData = new EditSectionInitialData( section.getName(), section
                .getDescription() );

        return initialData;
    }


    @Transactional
    @Override
    public SaveResponse save( EditSectionRequest request ) {

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        section.setName( request.getName() );
        section.setDescription( request.getDescription() );
        sectionRepository.save( section );

        return new SaveResponse( true, null );
    }

}
