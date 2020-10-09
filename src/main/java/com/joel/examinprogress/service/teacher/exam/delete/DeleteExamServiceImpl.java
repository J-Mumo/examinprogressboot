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
package com.joel.examinprogress.service.teacher.exam.delete;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.repository.exam.ExamRepository;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.service.shared.DeleteResponse;
import com.joel.examinprogress.service.teacher.exam.invite.delete.DeleteInviteService;
import com.joel.examinprogress.service.teacher.exam.section.delete.DeleteSectionService;

/**
 * @author Joel Mumo
 * @date   31st July, 2020
 */
@Service
public class DeleteExamServiceImpl implements DeleteExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private DeleteInviteService deleteInviteService;

    @Autowired
    private DeleteSectionService deleteSectionService;

    @Transactional
    @Override
    public DeleteResponse deleteExam( Long examId ) {

        Exam exam = examRepository.findById( examId ).get();
        Invite invite = exam.getInvite();
        Set<Section> sections = sectionRepository.findByExamId( examId );
        deleteInviteService.deleteInvite( invite.getId() );

        for ( Section section : sections ) {
            deleteSectionService.deleteSection( section.getId() );
        }

        examRepository.delete( exam );
        return new DeleteResponse( true, null );
    }

}
