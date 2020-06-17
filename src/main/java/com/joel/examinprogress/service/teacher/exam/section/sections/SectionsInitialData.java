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
package com.joel.examinprogress.service.teacher.exam.section.sections;

import com.joel.examinprogress.service.teacher.exam.section.shared.SectionTransfer;

/**
 * @author Joel Mumo
 * @date   16th June, 2020
 */
public class SectionsInitialData {

    private SectionTransfer[] sectionTransfers;

    public SectionsInitialData( SectionTransfer[] sectionTransfers ) {

        super();
        this.sectionTransfers = sectionTransfers;
    }


    public SectionTransfer[] getSectionTransfers() {

        return sectionTransfers;
    }


    public void setSectionTransfers( SectionTransfer[] sectionTransfers ) {

        this.sectionTransfers = sectionTransfers;
    }
}
