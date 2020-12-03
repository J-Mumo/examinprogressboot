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


/**
 * @author Joel Mumo
 * @date   Dec 2, 2020
 */
public class PaymentHistoryInitialData {

    private PaymentHistoryTransfer[] paymentHistoryTransfers;

    public PaymentHistoryInitialData( PaymentHistoryTransfer[] paymentHistoryTransfers ) {

        super();
        this.paymentHistoryTransfers = paymentHistoryTransfers;
    }


    /**
     * @return the paymentHistoryTransfers
     */
    public PaymentHistoryTransfer[] getPaymentHistoryTransfers() {

        return paymentHistoryTransfers;
    }


    /**
     * @param paymentHistoryTransfers the paymentHistoryTransfers to set
     */
    public void setPaymentHistoryTransfers( PaymentHistoryTransfer[] paymentHistoryTransfers ) {

        this.paymentHistoryTransfers = paymentHistoryTransfers;
    }
}
