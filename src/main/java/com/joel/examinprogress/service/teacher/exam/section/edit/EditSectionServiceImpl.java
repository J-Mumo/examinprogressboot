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

import java.time.Duration;

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
        Duration sectionDuration = section.getDuration();
        String duration = sectionDuration != null ? String.format( "%d:%02d:%02d", sectionDuration
                .getSeconds() / 3600,
                ( sectionDuration.getSeconds() % 3600 ) / 60, ( sectionDuration.getSeconds()
                        % 60 ) )
                : null;

        EditSectionInitialData initialData = new EditSectionInitialData( section.getName(), section
                .getDescription(), duration );

        return initialData;
    }


    @Transactional
    @Override
    public SaveResponse save( EditSectionRequest request ) {

        String duration[] = request.getDuration() != null ? request.getDuration().split( ":" )
                : null;
        String hour = duration != null ? duration[0] : "";
        String minute = duration != null ? duration[1] : "";
        String examDuration = "PT" + hour + "H" + minute + "M";
        Duration sectionDuration = request.getDuration() != null ? Duration.parse( examDuration )
                : null;

        Section section = sectionRepository.findById( request.getSectionId() ).get();
        section.setName( request.getName() );
        section.setDescription( request.getDescription() );
        section.setDuration( sectionDuration );
        sectionRepository.save( section );

        return new SaveResponse( true, null );
    }

}
