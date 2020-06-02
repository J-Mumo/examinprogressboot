package com.joel.examinprogress.service.shared;

/**
 * @author Joel Mumo
 * @date   28 May, 2020
 */
public class SaveResponse {

    private boolean saved;
    private String error;

    public SaveResponse() {

    }


    public SaveResponse( boolean saved, String error ) {

        this.saved = saved;
        this.error = error;
    }


    public String getError() {

        return error;
    }


    public void setError( String error ) {

        this.error = error;
    }


    public boolean isSaved() {

        return saved;
    }


    public void setSaved( boolean saved ) {

        this.saved = saved;
    }
}
