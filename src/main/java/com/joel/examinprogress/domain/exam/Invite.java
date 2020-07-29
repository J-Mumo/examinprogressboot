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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   25th June, 2020
 */
@Entity
@Table( name = "invite" )
public class Invite extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 9184192553295368727L;

    @Column( name = "name", nullable = false, unique = false,
            length = 100 )
    private String name;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }

    @Column( name = "exam_start_date", nullable = false )
    private Date examStartDate;

    @Column( name = "exam_end_date", nullable = true )
    private Date examEndDate;

    @Column( name = "exam_start_time", nullable = true )
    private Duration examStartTime;

    @Column( name = "pausable", nullable = false )
    private Boolean pausable;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey( name = "invite_fk_exam" ),
            nullable = false )
    private Exam exam;

    public Date getExamStartDate() {

        return examStartDate;
    }


    public void setExamStartDate( Date examStartDate ) {

        this.examStartDate = examStartDate;
    }


    public Date getExamEndDate() {

        return examEndDate;
    }


    public void setExamEndDate( Date examEndDate ) {

        this.examEndDate = examEndDate;
    }


    public Duration getExamStartTime() {

        return examStartTime;
    }


    public void setExamStartTime( Duration examStartTime ) {

        this.examStartTime = examStartTime;
    }


    public Boolean getPausable() {

        return pausable;
    }


    public void setPausable( Boolean pausable ) {

        this.pausable = pausable;
    }


    public Exam getExam() {

        return exam;
    }


    public void setExam( Exam exam ) {

        this.exam = exam;
    }
}
