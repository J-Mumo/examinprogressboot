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
package com.joel.examinprogress.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Entity
@Table( name = "country" )
public class Country extends AbstractPersistentEntity {

    private static final long serialVersionUID = -1086542741841292939L;

    @Column( name = "name", nullable = false, unique = false, length = 100 )
    private String name;

    @Column( name = "rb_key", nullable = false, unique = false, length = 100 )
    private String rbKey;

    @Column( name = "country_code", nullable = false, unique = false,
            length = 19 )
    private String countryCode;

    @Column( name = "enabled", nullable = false )
    private boolean enabled;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public String getRbKey() {

        return rbKey;
    }


    public void setRbKey( String rbKey ) {

        this.rbKey = rbKey;
    }


    public String getCountryCode() {

        return countryCode;
    }


    public void setCountryCode( String countryCode ) {

        this.countryCode = countryCode;
    }


    public boolean isEnabled() {

        return enabled;
    }


    public void setEnabled( boolean enabled ) {

        this.enabled = enabled;
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
