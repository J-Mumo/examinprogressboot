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

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.token.PaymentHistory;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.repository.token.PaymentHistoryRepository;

/**
 * @author Joel Mumo
 * @date   Dec 2, 2020
 */
@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private PaymentHistoryTransferComparator comparator;

    private PaymentHistoryTransfer createHistoryTransfer( PaymentHistory paymentHistory ) {

        PaymentHistoryTransfer transfer = new PaymentHistoryTransfer( paymentHistory.getId(),
                paymentHistory.getAmountPaid(), paymentHistory.getTokensBought(), paymentHistory
                        .getCurreny() );

        return transfer;
    }


    private PaymentHistoryTransfer[] createHistoryTransfers(
            Set<PaymentHistory> paymentHistories ) {

        SortedSet<PaymentHistoryTransfer> historyTransfers = new TreeSet<>( comparator );

        for ( PaymentHistory paymentHistory : paymentHistories ) {

            historyTransfers.add( createHistoryTransfer( paymentHistory ) );
        }

        return historyTransfers.toArray( new PaymentHistoryTransfer[historyTransfers.size()] );
    }


    @Override
    public PaymentHistoryInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );

        Set<PaymentHistory> paymentHistories = paymentHistoryRepository.findByTeacherId( teacher
                .getId() );

        PaymentHistoryTransfer[] historyTransfers = createHistoryTransfers( paymentHistories );
        return new PaymentHistoryInitialData( historyTransfers );
    }
}
