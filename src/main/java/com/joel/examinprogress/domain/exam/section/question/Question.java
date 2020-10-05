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

import java.time.Duration;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.section.Section;
import com.joel.examinprogress.domain.exam.section.question.answer.Answer;
import com.joel.examinprogress.domain.exam.section.question.answer.AnswerType;

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

    @Column( name = "question_text", nullable = false, columnDefinition = "TEXT" )
    private String questionText;

    @Column( name = "score", nullable = true )
    private Integer score;

    @Column( name = "duration", nullable = true )
    private Duration duration;

    @ManyToOne( )
    @JoinColumn( name = "fk_section",
            foreignKey = @ForeignKey( name = "question_fk_section" ),
            nullable = false )
    private Section section;

    @ManyToOne( )
    @JoinColumn( name = "fk_comprehension_question",
            foreignKey = @ForeignKey( name = "question_fk_comprehension_question" ),
            nullable = true )
    private Question question;

    @OneToOne( )
    @JoinColumn( name = "fk_answer_type",
            foreignKey = @ForeignKey( name = "question_fk_answer_type" ),
            nullable = false )
    private AnswerType answerType;

    @ManyToOne( )
    @JoinColumn( name = "fk_question_type",
            foreignKey = @ForeignKey( name = "question_fk_question_type" ),
            nullable = false )
    private QuestionType questionType;

    @OneToMany( )
    @JoinColumn( name = "fk_answer",
            foreignKey = @ForeignKey( name = "question_fk_answer" ),
            nullable = true )
    private Set<Answer> correctAnswers;

    // Questions in a comprehension question
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "question" )
    private Set<Question> questions;

    public String getQuestionText() {

        return questionText;
    }


    public void setQuestionText( String questionText ) {

        this.questionText = questionText;
    }


    public Integer getScore() {

        return score;
    }


    public Duration getDuration() {

        return duration;
    }


    public void setDuration( Duration duration ) {

        this.duration = duration;
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


    public Question getQuestion() {

        return question;
    }


    public void setQuestion( Question question ) {

        this.question = question;
    }


    public QuestionType getQuestionType() {

        return questionType;
    }


    public void setQuestionType( QuestionType questionType ) {

        this.questionType = questionType;
    }


    public AnswerType getAnswerType() {

        return answerType;
    }


    public void setAnswerType( AnswerType answerType ) {

        this.answerType = answerType;
    }


    public Set<Answer> getCorrectAnswers() {

        return correctAnswers;
    }


    public void setCorrectAnswers( Set<Answer> correctAnswers ) {

        this.correctAnswers = correctAnswers;
    }


    public Set<Question> getQuestions() {

        return questions;
    }


    public void setQuestions( Set<Question> questions ) {

        this.questions = questions;
    }
}
