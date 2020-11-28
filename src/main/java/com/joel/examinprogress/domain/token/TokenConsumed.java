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
package com.joel.examinprogress.domain.token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.teacher.Teacher;

/**
 * @author Joel Mumo
 * @date   Nov 27, 2020
 */
@Entity
@Table( name = "token_consumed" )
public class TokenConsumed extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7857932705324148438L;

    @Column( name = "email", nullable = false, unique = false )
    private String email;

    @ManyToOne( )
    @JoinColumn( name = "fk_teacher",
            foreignKey = @ForeignKey( name = "teacher_fk_token_consumed" ),
            nullable = false )
    private Teacher teacher;

    @ManyToOne( )
    @JoinColumn( name = "fk_student",
            foreignKey = @ForeignKey( name = "student_fk_token_consumed" ),
            nullable = true )
    private Student student;

    @ManyToOne( )
    @JoinColumn( name = "fk_exam",
            foreignKey = @ForeignKey( name = "exam_fk_token_consumed" ),
            nullable = false )
    private Exam exam;

    
    /**
     * @return the email
     */
    public String getEmail() {

        return email;
    }


    /**
     * @param email the email to set
     */
    public void setEmail( String email ) {

        this.email = email;
    }


    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
    
        return teacher;
    }

    
    /**
     * @param teacher the teacher to set
     */
    public void setTeacher( Teacher teacher ) {
    
        this.teacher = teacher;
    }

    
    /**
     * @return the student
     */
    public Student getStudent() {
    
        return student;
    }

    
    /**
     * @param student the student to set
     */
    public void setStudent( Student student ) {
    
        this.student = student;
    }

    
    /**
     * @return the exam
     */
    public Exam getExam() {
    
        return exam;
    }

    
    /**
     * @param exam the exam to set
     */
    public void setExam( Exam exam ) {
    
        this.exam = exam;
    }

}
