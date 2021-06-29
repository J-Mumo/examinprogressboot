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
package com.joel.examinprogress.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@MappedSuperclass
public class AbstractPersistentEntity implements Serializable {

    /**
     * Default version ID for serialization version management.
     */
    private static final long serialVersionUID = 1L;

    /**
     * You need to create a sequence in postgresql:
     *
     * CREATE SEQUENCE examinprogress_sequence START 10001;
     *
     * Starts at 10001 to allow for standing data. All standing data must have a
     * id of less than 10001 else duplicate key exceptions will occur
     */
    @Id
    @Column( name = "ID" )
    @SequenceGenerator( name = "identifier", sequenceName = "examinprogress_sequence",
            allocationSize = 1, initialValue = 10001 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "identifier" )
    @NotNull
    private Long id;

    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    @Column( name = "created_at", updatable = false, nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE" )
    private Calendar createdAt;

    @Temporal( TemporalType.TIMESTAMP )
    @UpdateTimestamp
    @Column( name = "updated_at", updatable = true, nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE" )
    private Calendar updatedAt;

    public Long getId() {

        return this.id;
    }


    public void setId( Long id ) {

        this.id = id;
    }


    public Calendar getCreatedAt() {

        return createdAt;
    }


    public void setCreatedAt( Calendar createdAt ) {

        this.createdAt = createdAt;
    }


    public Calendar getUpdatedAt() {

        return updatedAt;
    }


    public void setUpdatedAt( Calendar updatedAt ) {

        this.updatedAt = updatedAt;
    }


    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }


    @Override
    public boolean equals( Object obj ) {

        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        AbstractPersistentEntity other = ( AbstractPersistentEntity )obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        }
        else if ( !id.equals( other.id ) )
            return false;
        return true;
    }


    @Override
    public String toString() {

        return "AbstractPersistentEntity [id=" + id + "]";
    }
}
