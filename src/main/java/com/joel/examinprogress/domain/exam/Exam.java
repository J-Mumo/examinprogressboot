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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
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

    @Column( name = "duration", nullable = true, unique = false )
    private Duration duration;

    @ManyToOne( )
    @JoinColumn( name = "fk_teacher",
            foreignKey = @ForeignKey( name = "exam_fk_teacher" ),
            nullable = false )
    private Teacher teacher;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam_timer_type",
            foreignKey = @ForeignKey( name = "exam_fk_exam_timer_type" ),
            nullable = false )
    private ExamTimerType examTimerType;

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


    public Teacher getTeacher() {

        return teacher;
    }


    public void setTeacher( Teacher teacher ) {

        this.teacher = teacher;
    }


    public ExamTimerType getExamTimerType() {

        return examTimerType;
    }


    public void setExamTimerType( ExamTimerType examTimerType ) {

        this.examTimerType = examTimerType;
    }
}
