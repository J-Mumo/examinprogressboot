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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.service.teacher.dashboard.DashboardService;
import com.joel.examinprogress.service.teacher.token.payment.PaymentInitialData;
import com.joel.examinprogress.service.teacher.token.payment.PaymentService;

/**
 * @author Joel Mumo
 * @date   11th June, 2020
 */
@RestController
@RequestMapping( "/examinprogress/teacher/token" )
public class TeacherTokenController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @RequestMapping( value = "getinitialdata", method = RequestMethod.POST )
    public ResponseEntity<Integer> getTokens()
            throws IOException {

        Integer tokens = dashboardService.getTokens();
        return ResponseEntity.status( HttpStatus.OK ).body( tokens );
    }


    @RequestMapping( value = "payment/getinitialdata", method = RequestMethod.GET )
    public ResponseEntity<PaymentInitialData> getPaymentInitialData() {

        Long userId = loggedInCredentialsHelper.getLoggedInUser().getId();

        PaymentInitialData initialData = paymentService.getPaymentInitialData( userId );
        return ResponseEntity.status( HttpStatus.OK ).body( initialData );
    }
}
