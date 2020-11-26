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
package com.joel.examinprogress.service.teacher.token.payment;


/**
 * @author Joel Mumo
 * @date   Nov 26, 2020
 */
public class PaymentInitialData {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String currency;
    private String publicKey;
    private String paymentEndPoint;
    private Boolean production;

    public PaymentInitialData(
            Long userId,
            String firstName,
            String lastName,
            String email,
            String currency,
            String publicKey,
            String paymentEndPoint,
            Boolean production ) {

        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currency = currency;
        this.publicKey = publicKey;
        this.paymentEndPoint = paymentEndPoint;
        this.production = production;
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

    public String getCurrency() {

        return currency;
    }


    public void setCurrency( String currency ) {

        this.currency = currency;
    }


    public String getPublicKey() {

        return publicKey;
    }


    public void setPublicKey( String publicKey ) {

        this.publicKey = publicKey;
    }


    public String getPaymentEndPoint() {

        return paymentEndPoint;
    }


    public void setPaymentEndPoint( String paymentEndPoint ) {

        this.paymentEndPoint = paymentEndPoint;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId( Long userId ) {

        this.userId = userId;
    }


    public Boolean getProduction() {

        return production;
    }


    public void setProduction( Boolean production ) {

        this.production = production;
    }

}