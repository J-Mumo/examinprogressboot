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
package com.joel.examinprogress.domain.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   Apr 20, 2021
 */
@Entity
@Table( name = "contact_query" )
public class ContactQuery extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 6486275868169047186L;

    @Column( name = "name", nullable = false, unique = false, length = 100 )
    private String name;

    @Column( name = "email", nullable = false, unique = false, length = 255 )
    private String email;

    @Column( name = "message", nullable = false, unique = false)
    private String message;

    
    /**
     * @return the name
     */
    public String getName() {
    
        return name;
    }

    
    /**
     * @param name the name to set
     */
    public void setName( String name ) {
    
        this.name = name;
    }

    
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
     * @return the message
     */
    public String getMessage() {
    
        return message;
    }

    
    /**
     * @param message the message to set
     */
    public void setMessage( String message ) {
    
        this.message = message;
    }

}
