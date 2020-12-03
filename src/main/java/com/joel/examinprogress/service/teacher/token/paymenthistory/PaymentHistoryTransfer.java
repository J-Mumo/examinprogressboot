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
package com.joel.examinprogress.service.teacher.token.paymenthistory;

import java.math.BigDecimal;

/**
 * @author Joel Mumo
 * @date   Dec 2, 2020
 */
public class PaymentHistoryTransfer {

    private Long id;
    private BigDecimal amountPaid;
    private Integer tokensBought;
    private String currency;

    public PaymentHistoryTransfer( Long id, BigDecimal amountPaid, Integer tokensBought,
            String currency ) {

        super();
        this.id = id;
        this.amountPaid = amountPaid;
        this.tokensBought = tokensBought;
        this.currency = currency;
    }


    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId( Long id ) {

        this.id = id;
    }


    /**
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {

        return amountPaid;
    }


    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid( BigDecimal amountPaid ) {

        this.amountPaid = amountPaid;
    }


    /**
     * @return the tokensBought
     */
    public Integer getTokensBought() {

        return tokensBought;
    }


    /**
     * @param tokensBought the tokensBought to set
     */
    public void setTokensBought( Integer tokensBought ) {

        this.tokensBought = tokensBought;
    }


    /**
     * @return the currency
     */
    public String getCurrency() {

        return currency;
    }


    /**
     * @param currency the currency to set
     */
    public void setCurrency( String currency ) {

        this.currency = currency;
    }
}
