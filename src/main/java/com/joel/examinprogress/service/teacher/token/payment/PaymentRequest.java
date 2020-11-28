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
 * @date   Nov 27, 2020
 */
public class PaymentRequest {

    private Integer tokens;
    private PaymentSuccessResponse response;

    /**
     * @return the tokens
     */
    public Integer getTokens() {

        return tokens;
    }


    /**
     * @param tokens the tokens to set
     */
    public void setTokens( Integer tokens ) {

        this.tokens = tokens;
    }


    /**
     * @return the response
     */
    public PaymentSuccessResponse getResponse() {

        return response;
    }


    /**
     * @param response the response to set
     */
    public void setResponse( PaymentSuccessResponse response ) {

        this.response = response;
    }

}
