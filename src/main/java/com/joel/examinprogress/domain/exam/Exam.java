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
package com.joel.examinprogress.domain.exam;

import java.time.Duration;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.teacher.Teacher;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "exam" )
public class Exam extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4104392890396559528L;

    @Column( name = "name", nullable = false, unique = false,
            length = 100 )
    private String name;

    @Column( name = "description", nullable = false, unique = false,
            length = 1024 )
    private String description;

    @Column( name = "start_time", nullable = false, unique = false )
    private Calendar startTime;

    @Column( name = "duration", nullable = false, unique = false )
    private Duration duration;

    @Column( name = "complete", columnDefinition = "boolean default false", nullable = false )
    private String complete;

    @ManyToOne( )
    @JoinColumn( name = "fk_teacher",
            foreignKey = @ForeignKey( name = "exam_fk_teacher" ),
            nullable = false )
    private Teacher teacher;

    @OneToMany( mappedBy = "exam" )
    private Set<Section> sections;

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


    public Calendar getStartTime() {

        return startTime;
    }


    public void setStartTime( Calendar startTime ) {

        this.startTime = startTime;
    }


    public Duration getDuration() {

        return duration;
    }


    public void setDuration( Duration duration ) {

        this.duration = duration;
    }


    public String getComplete() {

        return complete;
    }


    public void setComplete( String complete ) {

        this.complete = complete;
    }


    public Teacher getTeacher() {

        return teacher;
    }


    public void setTeacher( Teacher teacher ) {

        this.teacher = teacher;
    }


    public Set<Section> getSections() {

        return sections;
    }


    public void setSections( Set<Section> sections ) {

        this.sections = sections;
    }
}
