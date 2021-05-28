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
 * @date   May 3, 2021
 */
@Entity
@Table( name = "mailing_list" )
public class MailingList extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1162953729017534497L;

    @Column( name = "email", nullable = false, unique = false, length = 255 )
    private String email;

    
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

}
