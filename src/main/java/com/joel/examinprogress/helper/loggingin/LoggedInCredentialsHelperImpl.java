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
package com.joel.examinprogress.helper.loggingin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.repository.user.UserRepository;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Component
public class LoggedInCredentialsHelperImpl implements
        LoggedInCredentialsHelper {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if ( authentication == null ) {

            return null;
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail( email );
        return user;
    }
}
