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
package com.joel.examinprogress;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@EntityScan( basePackages = { "com.joel.examinprogress.domain" } )
@SpringBootApplication
public class StartExamInProgress {

    public static void main( String[] args ) {

        Locale.setDefault( Locale.ENGLISH );
        SpringApplication.run( StartExamInProgress.class, args );
    }
}
