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

import java.time.LocalDateTime;

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

    @Column( name = "exam_start_date", nullable = false )
    private LocalDateTime examStartDate;

    @Column( name = "exam_end_date", nullable = true )
    private LocalDateTime examEndDate;

    @Column( name = "exam_start_time", nullable = true )
    private LocalDateTime examStartTime;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey( name = "invite_fk_exam" ),
            nullable = false )
    private Exam exam;

    public LocalDateTime getExamStartDate() {

        return examStartDate;
    }


    public void setExamStartDate( LocalDateTime examStartDate ) {

        this.examStartDate = examStartDate;
    }


    public LocalDateTime getExamEndDate() {

        return examEndDate;
    }


    public void setExamEndDate( LocalDateTime examEndDate ) {

        this.examEndDate = examEndDate;
    }


    public LocalDateTime getExamStartTime() {

        return examStartTime;
    }


    public void setExamStartTime( LocalDateTime examStartTime ) {

        this.examStartTime = examStartTime;
    }


    public Exam getExam() {

        return exam;
    }


    public void setExam( Exam exam ) {

        this.exam = exam;
    }
}
