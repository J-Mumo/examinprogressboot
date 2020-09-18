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
package com.joel.examinprogress.repository.student;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.student.SectionStatus;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
@Repository
public interface SectionStatusRepository extends JpaRepository<SectionStatus, Long> {

    SectionStatus findBySectionAndStudent( Section section, Student student );


    Set<SectionStatus> findByStudentAndSectionExam( Student student, Exam exam );

}
