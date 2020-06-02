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
