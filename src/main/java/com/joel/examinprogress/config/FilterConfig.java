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
