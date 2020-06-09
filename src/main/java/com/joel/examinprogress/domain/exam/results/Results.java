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
package com.joel.examinprogress.domain.exam.results;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "results" )
public class Results extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2308788963851258543L;

    @Column( name = "point_score", nullable = false, unique = false )
    private Integer pointScore;

    @Column( name = "percent_score", nullable = false, unique = false )
    private Integer percentScore;

    @ManyToOne( )
    @JoinColumn( name = "fk_result_type",
            foreignKey = @ForeignKey( name = "result_fk_result_type" ),
            nullable = false )
    private ResultType resultType;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey( name = "results_fk_exam" ),
            nullable = false )
    private Exam exam;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "results_fk_student" ),
            nullable = false )
    private Student student;

    public Integer getPointScore() {

        return pointScore;
    }


    public void setPointScore( Integer pointScore ) {

        this.pointScore = pointScore;
    }


    public Integer getPercentScore() {

        return percentScore;
    }


    public void setPercentScore( Integer percentScore ) {

        this.percentScore = percentScore;
    }


    public ResultType getResultType() {

        return resultType;
    }


    public void setResultType( ResultType resultType ) {

        this.resultType = resultType;
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
