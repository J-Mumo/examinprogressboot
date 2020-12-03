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

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.token.TokenConsumed;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.repository.token.TokenConsumedRepository;

/**
 * @author Joel Mumo
 * @date   Dec 2, 2020
 */
@Service
public class TokenConsumptionServiceImpl implements TokenConsumptionService {

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TokenConsumedRepository tokenConsumedRepository;

    @Autowired
    private TokenConsumedTransferComparator comparator;

    private TokenConsumedTransfer createTokenConsumedTransfer( TokenConsumed tokenConsumed ) {

        Long id = tokenConsumed.getId();
        String email = tokenConsumed.getEmail();
        String examName = tokenConsumed.getExam().getName();
        String studentName = "User is not yet registered";

        if ( tokenConsumed.getStudent() != null ) {

            User studentUser = tokenConsumed.getStudent().getUser();
            studentName = studentUser.getFirstName() + " " + studentUser.getLastName();
        }

        TokenConsumedTransfer transfer = new TokenConsumedTransfer( id, email, examName,
                studentName );

        return transfer;
    }


    private TokenConsumedTransfer[] createTokenConsumedTransfers(
            Set<TokenConsumed> tokenConsumeds ) {

        SortedSet<TokenConsumedTransfer> transfers = new TreeSet<>( comparator );

        for ( TokenConsumed tokenConsumed : tokenConsumeds ) {

            transfers.add( createTokenConsumedTransfer( tokenConsumed ) );
        }

        return transfers.toArray( new TokenConsumedTransfer[transfers.size()] );
    }


    @Override
    public TokenConsumptionInitialData getInitialData() {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );

        Set<TokenConsumed> tokenConsumeds = tokenConsumedRepository.findByTeacherId( teacher
                .getId() );

        TokenConsumedTransfer[] transfers = createTokenConsumedTransfers( tokenConsumeds );
        return new TokenConsumptionInitialData( transfers );
    }
}
