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
@Table( name = "address_type" )
public class AddressType extends AbstractPersistentEntity {

    private static final long serialVersionUID = 8150088901804004028L;

    @Column( name = "name", nullable = false, unique = false, length = 20 )
    private String name;

    public String getName() {

        return name;
    }


    public void setName( String name ) {

        this.name = name;
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
