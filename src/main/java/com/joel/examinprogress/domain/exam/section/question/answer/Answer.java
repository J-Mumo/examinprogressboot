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
package com.joel.examinprogress.domain.exam.section.question.answer;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.question.Question;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   16th July, 2020
 */
@Entity
@Table( name = "answer" )
public class Answer extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 6939196251681546886L;

    @Column( name = "answerText", nullable = false, unique = false,
            length = 1024 )
    private String answerText;

    @ManyToOne( )
    @JoinColumn( name = "fk_question",
            foreignKey = @ForeignKey( name = "answer_fk_question" ),
            nullable = false )
    private Question question;

    @ManyToMany( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "answer_fk_student" ),
            nullable = true )
    private Set<Student> students;

    @ManyToOne( )
    @JoinColumn( name = "fk_answer_type",
            foreignKey = @ForeignKey( name = "answer_fk_answer_type" ),
            nullable = false )
    private AnswerType answerType;

    public String getAnswerText() {

        return answerText;
    }


    public void setAnswerText( String answerText ) {

        this.answerText = answerText;
    }


    public Question getQuestion() {

        return question;
    }


    public void setQuestion( Question question ) {

        this.question = question;
    }


    /**
     * @return the students
     */
    public Set<Student> getStudents() {

        return students;
    }


    /**
     * @param students the students to set
     */
    public void setStudents( Set<Student> students ) {

        this.students = students;
    }


    public AnswerType getAnswerType() {

        return answerType;
    }


    public void setAnswerType( AnswerType answerType ) {

        this.answerType = answerType;
    }

}
