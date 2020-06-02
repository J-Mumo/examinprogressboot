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
