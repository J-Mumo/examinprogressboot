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

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "student" )
public class Student extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5779098414286870871L;

    @OneToOne( )
    @JoinColumn( name = "fk_user", foreignKey = @ForeignKey(
            name = "student_fk_user" ), nullable = false )
    private User user;

    @OneToMany( mappedBy = "student" )
    private Set<ExamToken> examTokens;

    public User getUser() {

        return user;
    }


    public void setUser( User user ) {

        this.user = user;
    }


    public Set<ExamToken> getExamTokens() {

        return examTokens;
    }


    public void setExamTokens( Set<ExamToken> examTokens ) {

        this.examTokens = examTokens;
    }
}
