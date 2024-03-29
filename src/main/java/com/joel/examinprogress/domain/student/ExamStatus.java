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

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.Exam;

/**
 * @author Joel Mumo
 * @date   3rd Sep, 2020
 */
@Entity
@Table( name = "exam_status" )
public class ExamStatus extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7966882574907572067L;

    @Column( name = "complete", nullable = false, unique = false )
    private Boolean complete;

    @Column( name = "started", nullable = false, unique = false )
    private Boolean started;

    @Column( name = "paused", nullable = false, unique = false )
    private Boolean paused;

    @Column( name = "paused_at", updatable = true, nullable = true,
            columnDefinition = "TIMESTAMP WITH TIME ZONE" )
    private Calendar pausedAt;

    @Column( name = "time_spent", updatable = true, nullable = false )
    private Long timeSpent;

    @Column( name = "scoring_complete", nullable = false, unique = false )
    private Boolean scoringComplete;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey(
                    name = "section_complete_fk_exam" ),
            nullable = false )
    private Exam exam;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "section_complete_fk_student" ),
            nullable = false )
    private Student student;

    public Boolean getComplete() {

        return complete;
    }


    public void setComplete( Boolean complete ) {

        this.complete = complete;
    }


    public Boolean getStarted() {

        return started;
    }


    public void setStarted( Boolean started ) {

        this.started = started;
    }


    public Boolean getPaused() {

        return paused;
    }


    public void setPaused( Boolean paused ) {

        this.paused = paused;
    }


    public Calendar getPausedAt() {

        return pausedAt;
    }


    public void setPausedAt( Calendar pausedAt ) {

        this.pausedAt = pausedAt;
    }

    public Long getTimeSpent() {

        return timeSpent;
    }


    public void setTimeSpent( Long timeSpent ) {

        this.timeSpent = timeSpent;
    }


    public Boolean getScoringComplete() {

        return scoringComplete;
    }


    public void setScoringComplete( Boolean scoringComplete ) {

        this.scoringComplete = scoringComplete;
    }


    public Exam getExam() {

        return exam;
    }


    public void setExam( Exam exam ) {

        this.exam = exam;
    }


    public Student getStudent() {

        return student;
    }


    public void setStudent( Student student ) {

        this.student = student;
    }
}
