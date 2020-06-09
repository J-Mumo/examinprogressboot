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
package com.joel.examinprogress.domain.exam.results;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   9th June, 2020
 */
@Entity
@Table( name = "result_type" )
public class ResultType extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2066452578951518357L;

    @Column( name = "name", nullable = false, unique = false,
            length = 50 )
    private String name;

    @OneToMany( mappedBy = "result_type" )
    private Set<Results> results;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
    }


    public Set<Results> getResults() {

        return results;
    }


    public void setResults( Set<Results> results ) {

        this.results = results;
    }
}
