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

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;

/**
 * @author Joel Mumo
 * @date   25th June, 2020
 */
@Entity
@Table( name = "comprehension_question" )
public class ComprehensionQuestion extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -7463941169539816604L;

    @Column( name = "questionText", nullable = false, unique = false,
            length = 1024 )
    private String questionText;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "multiple_choice_question_fk_section" ),
            nullable = false )
    private Section section;

    @OneToMany( )
    @JoinColumn( name = "fk_questions",
            foreignKey = @ForeignKey( name = "comprehension_question_fk_questions" ),
            nullable = false )
    private Set<Question> questions;

    public String getQuestionText() {

        return questionText;
    }


    public void setQuestionText( String questionText ) {

        this.questionText = questionText;
    }


    public Section getSection() {

        return section;
    }


    public void setSection( Section section ) {

        this.section = section;
    }


    public Set<Question> getQuestions() {

        return questions;
    }


    public void setQuestions( Set<Question> questions ) {

        this.questions = questions;
    }
}
