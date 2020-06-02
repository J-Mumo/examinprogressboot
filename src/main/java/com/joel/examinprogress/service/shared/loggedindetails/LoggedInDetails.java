package com.joel.examinprogress.service.shared.loggedindetails;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class LoggedInDetails {

    private Long userId;
    private String firstName;

    public LoggedInDetails() {

    }


    public LoggedInDetails( Long userId,
            String firstName ) {

        this.userId = userId;
        this.firstName = firstName;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId( Long userId ) {

        this.userId = userId;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName( String firstName ) {

        this.firstName = firstName;
    }
}
