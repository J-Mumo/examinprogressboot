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
import com.joel.examinprogress.domain.exam.section.question.Question;

/**
 * @author Joel Mumo
 * @date   3rd Sep, 2020
 */
@Entity
@Table( name = "question_status" )
public class QuestionStatus extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5815563770622095002L;

    @Column( name = "complete", nullable = false, unique = false )
    private Boolean complete;

    @Column( name = "fetched", nullable = false, unique = false )
    private Boolean fetched;

    @ManyToOne( )
    @JoinColumn( name = "fk_question",
            foreignKey = @ForeignKey( name = "question_complete_fk_question" ),
            nullable = false )
    private Question question;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "question_complete_fk_student" ),
            nullable = false )
    private Student student;

    public Boolean getComplete() {

        return complete;
    }


    public Boolean getFetched() {

        return fetched;
    }


    public void setFetched( Boolean fetched ) {

        this.fetched = fetched;
    }


    public void setComplete( Boolean complete ) {

        this.complete = complete;
    }


    public Question getQuestion() {

        return question;
    }


    public void setQuestion( Question question ) {

        this.question = question;
    }


    public Student getStudent() {

        return student;
    }


    public void setStudent( Student student ) {

        this.student = student;
    }
}
