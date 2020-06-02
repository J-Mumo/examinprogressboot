package com.joel.examinprogress.service.email;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class EmailActivationRequest {

    private String email;
    private String localeStr;

    public EmailActivationRequest( String email, String localeStr ) {

        this.email = email;
        this.localeStr = localeStr;
    }


    public EmailActivationRequest() {

        super();
    }


    public String getEmail() {

        return email;
    }


    public void setEmail( String email ) {

        this.email = email;
    }


    public String getLocaleStr() {

        return localeStr;
    }


    public void setLocaleStr( String localeStr ) {

        this.localeStr = localeStr;
    }
}
