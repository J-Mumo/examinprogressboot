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
package com.joel.examinprogress.service.register;

/**
 * @author Joel Mumo
 * @date   28 May, 2020
 */
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean inviteLink;
    private String code;

    public RegisterRequest( String firstName, String lastName, String email, String password,
            boolean inviteLink, String code ) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.inviteLink = inviteLink;
        this.code = code;
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


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }


    public String getPassword() {

        return password;
    }


    public void setPassword( String password ) {

        this.password = password;
    }


    public boolean isInviteLink() {

        return inviteLink;
    }


    public void setInviteLink( boolean inviteLink ) {

        this.inviteLink = inviteLink;
    }


    public String getCode() {

        return code;
    }


    public void setCode( String code ) {

        this.code = code;
    }
}
