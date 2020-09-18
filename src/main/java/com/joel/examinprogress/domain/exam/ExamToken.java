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
package com.joel.examinprogress.domain.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.student.Student;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "exam_token" )
public class ExamToken extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 3910693378116108040L;

    @Column( name = "email", nullable = false,
            unique = false,
            length = 256 )
    private String email;

    @Column( name = "token", nullable = false, unique = true,
            length = 128 )
    private String token;

    @ManyToOne( )
    @JoinColumn( name = "fk_invite",
            foreignKey = @ForeignKey( name = "exam_token_fk_invite" ),
            nullable = false )
    private Invite invite;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "exam_token_fk_student" ), nullable = true )
    private Student student;

    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }


    public String getToken() {

        return token;
    }


    public void setToken( String token ) {

        this.token = token;
    }



    public Invite getInvite() {

        return invite;
    }


    public void setInvite( Invite invite ) {

        this.invite = invite;
    }


    public Student getStudent() {

        return student;
    }


    public void setStudent( Student student ) {

        this.student = student;
    }
}
