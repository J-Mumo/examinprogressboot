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
package com.joel.examinprogress.domain.exam.section.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "multiple_choice_question" )
public class MultipleChoiceQuestion extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4052146954471159233L;

    @Column( name = "questionText", nullable = false, unique = false,
            length = 1024 )
    private String questionText;

    @Column( name = "score", nullable = false, unique = false )
    private Integer score;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "multiple_choice_question_fk_section" ),
            nullable = false )
    private Section section;

    public String getQuestionText() {

        return questionText;
    }


    public void setQuestionText( String questionText ) {

        this.questionText = questionText;
    }


    public Integer getScore() {

        return score;
    }


    public void setScore( Integer score ) {

        this.score = score;
    }


    public Section getSection() {

        return section;
    }


    public void setSection( Section section ) {

        this.section = section;
    }
}
