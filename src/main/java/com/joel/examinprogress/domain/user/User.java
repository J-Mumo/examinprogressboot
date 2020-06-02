package com.joel.examinprogress.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Entity
@Table( name = "app_user" )
public class User extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 812923710931154224L;

    @Column( name = "enabled", nullable = false )
    private Boolean enabled;

    @Column( name = "password_hash", nullable = false, unique = false,
            length = 255 )
    @JsonIgnore
    private String passwordHash;

    @Column( name = "email", nullable = false, unique = true,
            length = 255 )
    private String email;

    @Column( name = "first_name", nullable = false, unique = false,
            length = 100 )
    private String firstName;

    @Column( name = "last_name", nullable = false, unique = false,
            length = 100 )
    private String lastName;

    @Column( name = "email_activation_code", nullable = true, unique = false,
            length = 255 )
    private String emailActivationCode;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "user_role_fk_user" ) ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "user_role_fk_role" ) ) )
    private Set<Role> roles = new HashSet<Role>();

    @ManyToOne( )
    @JoinColumn( name = "fk_domain_organisation",
            foreignKey = @ForeignKey( name = "user_fk_domain_organization" ),
            nullable = false )
    private DomainOrganisation domainOrganisation;

    public Boolean getEnabled() {

        return enabled;
    }


    public void setEnabled( Boolean enabled ) {

        this.enabled = enabled;
    }


    public String getPasswordHash() {

        return passwordHash;
    }


    public void setPasswordHash( String passwordHash ) {

        this.passwordHash = passwordHash;
    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName( String firstName ) {

        this.firstName = firstName;
    }


    public String getLastName() {

        return lastName;
    }


    public void setLastName( String lastName ) {

        this.lastName = lastName;
    }


    public String getEmailActivationCode() {

        return emailActivationCode;
    }


    public void setEmailActivationCode( String emailActivationCode ) {

        this.emailActivationCode = emailActivationCode;
    }


    public Set<Role> getRoles() {

        return roles;
    }


    public void setRoles( Set<Role> roles ) {

        this.roles = roles;
    }


    public DomainOrganisation getDomainOrganisation() {

        return domainOrganisation;
    }


    public void setDomainOrganisation( DomainOrganisation domainOrganisation ) {

        this.domainOrganisation = domainOrganisation;
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
