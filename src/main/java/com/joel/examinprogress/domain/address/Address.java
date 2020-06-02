package com.joel.examinprogress.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Entity
@Table( name = "address" )
public class Address extends AbstractPersistentEntity {

    private static final long serialVersionUID = 4699044191347913291L;

    @Column( name = "first_line", nullable = false, unique = false,
            length = 100 )
    private String firstLine;

    @Column( name = "second_line", nullable = true, unique = false,
            length = 100 )
    private String secondLine;

    @Column( name = "town_or_city", nullable = false, unique = false,
            length = 100 )
    private String townOrCity;

    @Column( name = "post_code", nullable = true, unique = false, length = 100 )
    private String postCode;

    @ManyToOne( )
    @JoinColumn( name = "fk_country",
            foreignKey = @ForeignKey( name = "address_fk_country" ),
            nullable = false )
    private Country country;

    @ManyToOne( )
    @JoinColumn( name = "fk_address_type",
            foreignKey = @ForeignKey( name = "address_fk_address_type" ),
            nullable = false )
    private AddressType addressType;

    public String getFirstLine() {

        return firstLine;
    }


    public void setFirstLine( String firstLine ) {

        this.firstLine = firstLine;
    }


    public String getSecondLine() {

        return secondLine;
    }


    public void setSecondLine( String secondLine ) {

        this.secondLine = secondLine;
    }


    public String getTownOrCity() {

        return townOrCity;
    }


    public void setTownOrCity( String townOrCity ) {

        this.townOrCity = townOrCity;
    }


    public String getPostCode() {

        return postCode;
    }


    public void setPostCode( String postCode ) {

        this.postCode = postCode;
    }


    public Country getCountry() {

        return country;
    }


    public void setCountry( Country country ) {

        this.country = country;
    }


    public AddressType getAddressType() {

        return addressType;
    }


    public void setAddressType( AddressType addressType ) {

        this.addressType = addressType;
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
