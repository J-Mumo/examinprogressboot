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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.answer.ImageAnswer;
import com.joel.examinprogress.domain.exam.section.question.answer.MultipleChoiceAnswer;
import com.joel.examinprogress.domain.exam.section.question.answer.TextAnswer;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "question" )
public class Question extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4052146954471159233L;

    @Column( name = "question_text", nullable = false, unique = false,
            length = 1024 )
    private String questionText;

    @Column( name = "score", nullable = false, unique = false )
    private Integer score;

    @Column( name = "answer_type", nullable = false, unique = false,
            length = 50 )
    private String answerType;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "question_fk_section" ),
            nullable = false )
    private Section section;

    @ManyToOne( )
    @JoinColumn( name = "fk_comprehension_question",
            foreignKey = @ForeignKey( name = "question_fk_comprehension_question" ),
            nullable = true )
    private ComprehensionQuestion comprehensionQuestion;

    @OneToMany( )
    @JoinColumn( name = "fk_multiple_choice_answer",
            foreignKey = @ForeignKey( name = "question_fk_multiple_choice_answer" ),
            nullable = true )
    private Set<MultipleChoiceAnswer> multipleChoiceAnswers;

    @OneToOne( )
    @JoinColumn( name = "fk_text_answer",
            foreignKey = @ForeignKey( name = "question_fk_text_answer" ),
            nullable = true )
    private TextAnswer textAnswer;

    @OneToOne( )
    @JoinColumn( name = "fk_image_answer",
            foreignKey = @ForeignKey( name = "question_fk_image_answer" ),
            nullable = true )
    private ImageAnswer imageAnswer;

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


    public String getAnswerType() {

        return answerType;
    }


    public void setAnswerType( String answerType ) {

        this.answerType = answerType;
    }


    public Section getSection() {

        return section;
    }


    public void setSection( Section section ) {

        this.section = section;
    }


    public ComprehensionQuestion getComprehensionQuestion() {

        return comprehensionQuestion;
    }


    public void setComprehensionQuestion( ComprehensionQuestion comprehensionQuestion ) {

        this.comprehensionQuestion = comprehensionQuestion;
    }


    public Set<MultipleChoiceAnswer> getMultipleChoiceAnswers() {

        return multipleChoiceAnswers;
    }


    public void setMultipleChoiceAnswers( Set<MultipleChoiceAnswer> multipleChoiceAnswers ) {

        this.multipleChoiceAnswers = multipleChoiceAnswers;
    }


    public TextAnswer getTextAnswer() {

        return textAnswer;
    }


    public void setTextAnswer( TextAnswer textAnswer ) {

        this.textAnswer = textAnswer;
    }


    public ImageAnswer getImageAnswer() {

        return imageAnswer;
    }


    public void setImageAnswer( ImageAnswer imageAnswer ) {

        this.imageAnswer = imageAnswer;
    }
}
