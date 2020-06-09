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
package com.joel.examinprogress.service.shared.loggedindetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Service
public class LoggedInUserDetailsServiceImpl implements
        LoggedInUserDetailsService {

    @Autowired
    private com.joel.examinprogress.repository.user.UserRepository userRepository;

    @Override
    public LoggedInUserDetails getLoggedInUserDetails( Long userId ) {

        com.joel.examinprogress.domain.user.User user = userRepository.findById( userId ).get();

        LoggedInUserDetails loggedInDetails = new LoggedInUserDetails( user
                .getId(), user.getEmail() );

        return loggedInDetails;
    }
}
