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
package com.joel.examinprogress.controller.teacher;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.service.teacher.token.payment.PaymentInitialData;
import com.joel.examinprogress.service.teacher.token.payment.PaymentRequest;
import com.joel.examinprogress.service.teacher.token.payment.PaymentService;
import com.joel.examinprogress.service.teacher.token.payment.UpdateTokenResponse;
import com.joel.examinprogress.service.teacher.token.paymenthistory.PaymentHistoryInitialData;
import com.joel.examinprogress.service.teacher.token.paymenthistory.PaymentHistoryService;
import com.joel.examinprogress.service.teacher.token.tokenconsumption.TokenConsumptionInitialData;
import com.joel.examinprogress.service.teacher.token.tokenconsumption.TokenConsumptionService;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/token" )
public class TeacherTokenController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @Autowired
    private TokenConsumptionService tokenConsumptionService;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @RequestMapping( value = "getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<Integer> getTokens()
            throws IOException {

        Integer tokens = paymentService.getTokens();
        return ResponseEntity.status( HttpStatus.OK ).body( tokens );
    }


    @RequestMapping( value = "payment/getinitialdata", method = RequestMethod.GET )
    public ResponseEntity<PaymentInitialData> getPaymentInitialData() {

        Long userId = loggedInCredentialsHelper.getLoggedInUser().getId();

        PaymentInitialData initialData = paymentService.getPaymentInitialData( userId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "updatetokens", method = RequestMethod.POST )
    public ResponseEntity<UpdateTokenResponse> updateTokens( @RequestBody PaymentRequest request )
            throws IOException {

        UpdateTokenResponse response = paymentService.updateTokens( request );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "paymenthistory/getinitialdata", method = RequestMethod.GET )
    public ResponseEntity<PaymentHistoryInitialData> getPaymentHistoryInitialData() {

        PaymentHistoryInitialData initialData = paymentHistoryService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }


    @RequestMapping( value = "tokenconsumption/getinitialdata", method = RequestMethod.GET )
    public ResponseEntity<TokenConsumptionInitialData> getTokenonsumptionInitialData() {

        TokenConsumptionInitialData initialData = tokenConsumptionService.getInitialData();
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
