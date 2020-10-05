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
package com.joel.examinprogress.domain.organisation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Entity
@Table( name = "domain_organisation" )
public class DomainOrganisation extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -4086088830117319039L;

    @Column( name = "name", nullable = false, unique = false, length = 30 )
    private String name;

    @Column( name = "domain", nullable = false, unique = false, length = 254 )
    private String domain;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public String getDomain() {

        return domain;
    }


    public void setDomain( String domain ) {

        this.domain = domain;
    }


    @Override
    public int hashCode() {

        return super.hashCode();
    }


    @Override
    public boolean equals( Object obj ) {

        return super.equals( obj );
    }
}
