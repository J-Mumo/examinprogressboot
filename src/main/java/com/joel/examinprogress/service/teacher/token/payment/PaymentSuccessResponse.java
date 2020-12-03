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

import java.math.BigDecimal;

/**
 * @author Joel Mumo
 * @date   Nov 27, 2020
 */
public class PaymentSuccessResponse {

    private BigDecimal amount;
    private String currency;
    private Object customer;
    private String flw_ref;
    private String status;
    private Integer transaction_id;
    private String tx_ref;
    private String payment_plan;

    public PaymentSuccessResponse( BigDecimal amount, String currency, Object customer,
            String flw_ref, String status, Integer transaction_id, String tx_ref,
            String payment_plan ) {

        super();
        this.amount = amount;
        this.currency = currency;
        this.customer = customer;
        this.flw_ref = flw_ref;
        this.status = status;
        this.transaction_id = transaction_id;
        this.tx_ref = tx_ref;
        this.payment_plan = payment_plan;
    }


    /**
     * @return the amount
     */
    public BigDecimal getAmount() {

        return amount;
    }


    /**
     * @param amount the amount to set
     */
    public void setAmount( BigDecimal amount ) {

        this.amount = amount;
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


    /**
     * @return the customer
     */
    public Object getCustomer() {

        return customer;
    }


    /**
     * @param customer the customer to set
     */
    public void setCustomer( Object customer ) {

        this.customer = customer;
    }


    /**
     * @return the flw_ref
     */
    public String getFlw_ref() {

        return flw_ref;
    }


    /**
     * @param flw_ref the flw_ref to set
     */
    public void setFlw_ref( String flw_ref ) {

        this.flw_ref = flw_ref;
    }


    /**
     * @return the status
     */
    public String getStatus() {

        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus( String status ) {

        this.status = status;
    }


    /**
     * @return the transaction_id
     */
    public Integer getTransaction_id() {

        return transaction_id;
    }


    /**
     * @param transaction_id the transaction_id to set
     */
    public void setTransaction_id( Integer transaction_id ) {

        this.transaction_id = transaction_id;
    }


    /**
     * @return the tx_ref
     */
    public String getTx_ref() {

        return tx_ref;
    }


    /**
     * @param tx_ref the tx_ref to set
     */
    public void setTx_ref( String tx_ref ) {

        this.tx_ref = tx_ref;
    }


    /**
     * @return the payment_plan
     */
    public String getPayment_plan() {

        return payment_plan;
    }


    /**
     * @param payment_plan the payment_plan to set
     */
    public void setPayment_plan( String payment_plan ) {

        this.payment_plan = payment_plan;
    }
}
