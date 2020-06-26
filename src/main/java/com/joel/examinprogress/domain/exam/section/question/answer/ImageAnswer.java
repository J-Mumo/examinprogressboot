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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.question.Question;

/**
 * @author Joel Mumo
 * @date   25th June, 2020
 */
@Entity
@Table( name = "image_answer" )
public class ImageAnswer extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -6816947449326529070L;

    @Column( name = "answerText", nullable = false, unique = false )
    private String answerText;

    @OneToOne( )
    @JoinColumn( name = "fk_question",
            foreignKey = @ForeignKey( name = "multiple_choice_answer_fk_question" ),
            nullable = false )
    private Question question;

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

}
