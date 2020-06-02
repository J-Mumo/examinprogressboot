package com.joel.examinprogress.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Joel Mumo
 * @date   29 May 2020
 */
@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Value( "${security.jwt.resource-ids}" )
    private String resourceIds;

    @Override
    public void configure( ResourceServerSecurityConfigurer resources )
            throws Exception {

        resources.resourceId( resourceIds );
    }
}
