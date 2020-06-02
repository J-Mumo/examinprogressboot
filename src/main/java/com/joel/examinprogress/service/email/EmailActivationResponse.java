package com.joel.examinprogress.service.email;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class EmailActivationResponse {

    private boolean emailActivation;
    private String error;

    public EmailActivationResponse( boolean emailActivation, String error ) {

        super();
        this.emailActivation = emailActivation;
        this.error = error;
    }


    public EmailActivationResponse() {

        super();
    }


    public boolean isEmailActivation() {

        return emailActivation;
    }


    public void setEmailActivation( boolean emailActivation ) {

        this.emailActivation = emailActivation;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }
}
