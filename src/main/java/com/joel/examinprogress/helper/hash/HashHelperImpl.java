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
