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

/**
 * @author Joel Mumo
 * @date   4th July, 2020
 */
public class CreateSectionInitialData {

    private boolean examTimedBySection;

    public CreateSectionInitialData( boolean examTimedBySection ) {

        super();
        this.examTimedBySection = examTimedBySection;
    }


    public boolean isExamTimedBySection() {

        return examTimedBySection;
    }


    public void setExamTimedBySection( boolean examTimedBySection ) {

        this.examTimedBySection = examTimedBySection;
    }
}
