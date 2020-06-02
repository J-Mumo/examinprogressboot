package com.joel.examinprogress.helper.hash;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface HashHelper {

    String getRandomHash();


    String getPasswordHashWithBcrypt( String password );
}
