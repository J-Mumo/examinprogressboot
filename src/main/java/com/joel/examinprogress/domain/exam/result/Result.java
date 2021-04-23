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
package com.joel.examinprogress.domain.exam.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "result" )
public class Result extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2308788963851258543L;

    @Column( name = "point_score", nullable = true, unique = false )
    private Integer pointScore = 0;

    @Column( name = "percent_score", nullable = true, unique = false )
    private Float percentScore = 0f;

    @Column( name = "total_score", nullable = false, unique = false )
    private Integer totalScore = 0;

    @ManyToOne( )
    @JoinColumn( name = "fk_result_type",
            foreignKey = @ForeignKey( name = "result_fk_result_type" ),
            nullable = false )
    private ResultType resultType;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "result_fk_student" ),
            nullable = false )
    private Student student;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey(
                    name = "result_fk_exam" ),
            nullable = true )
    private Exam exam;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "result_fk_section" ),
            nullable = true )
    private Section section;

    @ManyToOne( )
    @JoinColumn( name = "fk_question",
            foreignKey = @ForeignKey(
                    name = "result_fk_question" ),
            nullable = true )
    private Question question;

    public Integer getPointScore() {

        return pointScore;
    }


    public void setPointScore( Integer pointScore ) {

        this.pointScore = pointScore;
    }


    public Float getPercentScore() {

        return percentScore;
    }


    public void setPercentScore( Float percentScore ) {

        this.percentScore = percentScore;
    }


    public Integer getTotalScore() {

        return totalScore;
    }


    public void setTotalScore( Integer totalScore ) {

        this.totalScore = totalScore;
    }



    public ResultType getResultType() {

        return resultType;
    }


    public void setResultType( ResultType resultType ) {

        this.resultType = resultType;
    }


    public Student getStudent() {

        return student;
    }


    public void setStudent( Student student ) {

        this.student = student;
    }


    public Exam getExam() {

        return exam;
    }



    public void setExam( Exam exam ) {

        this.exam = exam;
    }


    public Section getSection() {

        return section;
    }


    public void setSection( Section section ) {

        this.section = section;
    }


    public Question getQuestion() {

        return question;
    }


    public void setQuestion( Question question ) {

        this.question = question;
    }
}
