package com.joel.examinprogress.helper.email;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class EmailSentResponse {

    private boolean emailSent;
    private String error;

    public EmailSentResponse( boolean emailSent, String error ) {

        super();
        this.emailSent = emailSent;
        this.error = error;
    }


    public EmailSentResponse() {

        super();
    }


    public boolean isEmailSent() {

        return emailSent;
    }


    public void setEmailSent( boolean emailSent ) {

        this.emailSent = emailSent;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }
}
