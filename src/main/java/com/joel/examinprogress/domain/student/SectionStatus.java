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
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;

/**
 * @author Joel Mumo
 * @date   3rd Sep, 2020
 */
@Entity
@Table( name = "section_status" )
public class SectionStatus extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7966882574907572067L;

    @Column( name = "complete", nullable = false, unique = false )
    private Boolean complete;

    @Column( name = "time_spent", updatable = true, nullable = false )
    private Long timeSpent;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "section_status_fk_section" ),
            nullable = false )
    private Section section;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "section_status_fk_student" ),
            nullable = false )
    private Student student;

    public Boolean getComplete() {

        return complete;
    }


    public void setComplete( Boolean complete ) {

        this.complete = complete;
    }


    public Long getTimeSpent() {

        return timeSpent;
    }


    public void setTimeSpent( Long timeSpent ) {

        this.timeSpent = timeSpent;
    }


    public Section getSection() {

        return section;
    }


    public void setSection( Section section ) {

        this.section = section;
    }


    public Student getStudent() {

        return student;
    }


    public void setStudent( Student student ) {

        this.student = student;
    }
}
