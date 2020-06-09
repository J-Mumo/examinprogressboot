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
package com.joel.examinprogress.helper.hash;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Component
public class HashHelperImpl implements HashHelper {

    private SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getPasswordHashWithBcrypt( String password ) {

        //        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode( password );
        return hashedPassword;
    }


    @Override
    public String getRandomHash() {

        return secureRandom.nextLong() + "";
    }


    public static void main( String[] args ) {

        HashHelper hashHelper = new HashHelperImpl();
        String password = hashHelper.getPasswordHashWithBcrypt( "blah" );
        System.out.println( password );
    }
}
