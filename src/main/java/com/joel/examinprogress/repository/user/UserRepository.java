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
package com.joel.examinprogress.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail( String email );


    User findByEmailActivationCode( String emailActivationCode );
}
