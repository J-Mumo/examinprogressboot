package com.joel.examinprogress.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;

/**
 * @author John Dickerson
 * @date   14th August 2019
 */
@Entity
@Table( name = "forgotten_password" )
public class ForgottenPassword extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -441828843561781917L;

    @Column( name = "code", nullable = false, unique = false, length = 255 )
    private String code;

    @Column( name = "active", nullable = false )
    private Boolean active;

    @OneToOne
    @JoinColumn( name = "fk_user",
            foreignKey = @ForeignKey( name = "forgotten_password_fk_user" ) )
    private User user;

    public String getCode() {

        return code;
    }


    public void setCode( String code ) {

        this.code = code;
    }


    public Boolean getActive() {

        return active;
    }


    public void setActive( Boolean active ) {

        this.active = active;
    }


    public User getUser() {

        return user;
    }


    public void setUser( User user ) {

        this.user = user;
    }


    @Override
    public int hashCode() {

        return super.hashCode();
    }


    @Override
    public boolean equals( Object obj ) {

        return super.equals( obj );
    }


    @Override
    public String toString() {

        return "ForgottenPassword [code=" + code + ", active=" + active + ", getId()=" + getId()
                + "]";
    }
}
