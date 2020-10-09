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
package com.joel.examinprogress.service.teacher.exam.view;

import java.time.Duration;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.service.teacher.exam.shared.SectionTransfer;
import com.joel.examinprogress.service.teacher.exam.shared.SectionTransferComparator;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
@Service
public class ViewExamServiceImpl implements ViewExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionTransferComparator sectionTransferComparator;

    private SectionTransfer createSectionTransfer( Section section ) {

        SectionTransfer transfer = new SectionTransfer(
                section.getId(),
                section.getName(),
                section.getDescription() );

        return transfer;
    }


    private SectionTransfer[] createSectionTransfers( Set<Section> sections ) {

        SortedSet<SectionTransfer> sectionTransfers =
                new TreeSet<>( sectionTransferComparator );

        for ( Section section : sections ) {

            sectionTransfers.add( createSectionTransfer( section ) );
        }

        return sectionTransfers.toArray( new SectionTransfer[sectionTransfers.size()] );
    }


    @Override
    public ViewExamInitialData getInitialData( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        Set<Section> sections = sectionRepository.findByExamId( examId );
        SectionTransfer[] sectionTransfers = createSectionTransfers( sections );
        Duration examDuration = exam.getDuration();
        boolean hasInvites = false;

        if ( exam.getInvite() != null )
            hasInvites = true;

        String duration = examDuration != null ? String.format( "%d:%02d:%02d", examDuration
                .getSeconds() / 3600, ( examDuration.getSeconds() % 3600 ) / 60, ( examDuration
                        .getSeconds() % 60 ) ) : null;

        ViewExamInitialData initialData = new ViewExamInitialData(
                exam.getName(), exam.getDescription(), duration, sectionTransfers, hasInvites );

        return initialData;
    }

}
