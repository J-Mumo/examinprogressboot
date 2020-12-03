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
package com.joel.examinprogress.service.teacher.token.tokenconsumption;


/**
 * @author Joel Mumo
 * @date   Dec 2, 2020
 */
public class TokenConsumptionInitialData {

    private TokenConsumedTransfer[] tokenConsumedTransfers;

    public TokenConsumptionInitialData( TokenConsumedTransfer[] tokenConsumedTransfers ) {

        super();
        this.tokenConsumedTransfers = tokenConsumedTransfers;
    }


    /**
     * @return the tokenConsumedTransfers
     */
    public TokenConsumedTransfer[] getTokenConsumedTransfers() {

        return tokenConsumedTransfers;
    }


    /**
     * @param tokenConsumedTransfers the tokenConsumedTransfers to set
     */
    public void setTokenConsumedTransfers( TokenConsumedTransfer[] tokenConsumedTransfers ) {

        this.tokenConsumedTransfers = tokenConsumedTransfers;
    }
}
