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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.question.MultipleChoiceQuestion;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "answer" )
public class Answer extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -8945303625987527128L;

    @Column( name = "answerText", nullable = false, unique = false,
            length = 1024 )
    private String questionText;

    @Column( name = "correct", columnDefinition = "boolean default false", nullable = false )
    private Boolean correct;

    @ManyToOne( )
    @JoinColumn( name = "fk_multiple_choice_question",
            foreignKey = @ForeignKey( name = "answer_fk_multiple_choice_question" ),
            nullable = false )
    private MultipleChoiceQuestion multipleChoiceQuestion;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "answer_fk_student" ),
            nullable = false )
    private Student student;

}
