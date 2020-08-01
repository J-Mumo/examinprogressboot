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
package com.joel.examinprogress.service.teacher.exam.section.delete;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.repository.exam.section.SectionRepository;
import com.joel.examinprogress.repository.exam.section.question.QuestionRepository;
import com.joel.examinprogress.service.shared.DeleteResponse;
import com.joel.examinprogress.service.teacher.exam.section.question.delete.DeleteQuestionService;

/**
 * @author Joel Mumo
 * @date   31st July, 2020
 */
@Service
public class DeleteSectionServiceImpl implements DeleteSectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DeleteQuestionService deleteQuestionService;

    @Transactional
    @Override
    public DeleteResponse deleteSection( Long sectionId ) {

        Section section = sectionRepository.findById( sectionId ).get();
        Set<Question> questions = questionRepository.findBySectionId( sectionId );
        for ( Question question : questions ) {
            deleteQuestionService.deleteQuestion( question.getId() );
        }
        sectionRepository.delete( section );

        return new DeleteResponse( true, null );
    }

}
