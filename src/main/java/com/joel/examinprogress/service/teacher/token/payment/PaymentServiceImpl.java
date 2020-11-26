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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.repository.teacher.TeacherRepository;

/**
 * @author Joel Mumo
 * @date   Nov 26, 2020
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value( "${flutterwave.public_key}" )
    private String publicKey;

    @Value( "${flutterwave.payment-endpoint}" )
    private String PAYMENT_ENDPOINT;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public PaymentInitialData getPaymentInitialData( Long userId ) {

        Teacher teacher = teacherRepository.findByUserId( userId );
        User user = teacher.getUser();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String currency = "KES";
        Boolean production = PAYMENT_ENDPOINT.contains( "ravesandboxapi" ) ? false : true;

        PaymentInitialData initialData = new PaymentInitialData( userId, firstName, lastName, email,
                currency, publicKey, PAYMENT_ENDPOINT, production );

        return initialData;
    }
}
