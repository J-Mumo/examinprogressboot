package com.joel.examinprogress.service.email;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public class ForgottenPasswordRequest {

    private String email;

    public ForgottenPasswordRequest() {

    }


    public ForgottenPasswordRequest( String email ) {

    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }
}
