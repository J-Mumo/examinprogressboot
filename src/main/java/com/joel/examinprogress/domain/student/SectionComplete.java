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
package com.joel.examinprogress.domain.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   12th Aug, 2020
 */
@Entity
@Table( name = "section_complete" )
public class SectionComplete extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7966882574907572067L;

    @Column( name = "complete", nullable = false, unique = false )
    private Boolean complete;

    public Boolean getComplete() {

        return complete;
    }


    public void setComplete( Boolean complete ) {

        this.complete = complete;
    }
}
