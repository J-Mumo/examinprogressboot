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
package com.joel.examinprogress.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Joel Mumo
 * @date   1 June 2020
 */
@Configuration
public class ResourceBundleConfig {

    @Bean
    public MessageSource resourceBundleMessageSource() {

        ResourceBundleMessageSource resourceBundleMessageSource =
                new ResourceBundleMessageSource();

        resourceBundleMessageSource.setBasenames( "email" );
        return resourceBundleMessageSource;
    }
}
