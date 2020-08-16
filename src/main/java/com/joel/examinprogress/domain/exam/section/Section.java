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
package com.joel.examinprogress.domain.exam.section;

import java.time.Duration;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.section.question.Question;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "section" )
public class Section extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -8438237560355645714L;

    @Column( name = "name", nullable = false, unique = false,
            length = 100 )
    private String name;

    @Column( name = "description", nullable = true, unique = false,
            length = 1024 )
    private String description;

    @Column( name = "duration", nullable = true, unique = false )
    private Duration duration;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey( name = "section_fk_exam" ),
            nullable = false )
    private Exam exam;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "section" )
    private Set<Question> questions;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public String getDescription() {

        return description;
    }


    public void setDescription( String description ) {

        this.description = description;
    }


    public Duration getDuration() {

        return duration;
    }


    public void setDuration( Duration duration ) {

        this.duration = duration;
    }


    public Exam getExam() {

        return exam;
    }


    public void setExam( Exam exam ) {

        this.exam = exam;
    }


    public Set<Question> getQuestions() {

        return questions;
    }


    public void setQuestions( Set<Question> questions ) {

        this.questions = questions;
    }
}
