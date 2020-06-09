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

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.joel.examinprogress.filter.DomainProtocolPortFilter;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Configuration
public class FilterConfig {

    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Bean
    @Order( 0 )
    public FilterRegistrationBean domainFilter() {

        FilterRegistrationBean domainFilter = new FilterRegistrationBean();
        domainFilter.setFilter( new DomainProtocolPortFilter() );
        return domainFilter;
    }
}
