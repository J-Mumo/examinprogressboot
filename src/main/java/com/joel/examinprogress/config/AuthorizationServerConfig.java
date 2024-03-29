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

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthorizationServerConfig extends
        AuthorizationServerConfigurerAdapter {

    @Value( "${security.jwt.client-id}" )
    private String clientId;

    @Value( "${security.jwt.client-secret}" )
    private String clientSecret;

    @Value( "${security.jwt.grant-type}" )
    private String grantType;

    @Value( "${security.jwt.scope-read}" )
    private String scopeRead;

    @Value( "${security.jwt.scope-write}" )
    private String scopeWrite = "write";

    @Value( "${security.jwt.resource-ids}" )
    private String resourceIds;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure( ClientDetailsServiceConfigurer configurer )
            throws Exception {

        configurer.inMemory().withClient( clientId )
                .secret( passwordEncoder.encode( clientSecret ) )
                .authorizedGrantTypes( grantType ).scopes( scopeRead,
                        scopeWrite ).resourceIds( resourceIds );
    }


    @Override
    public void configure( AuthorizationServerEndpointsConfigurer endpoints )
            throws Exception {

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

        enhancerChain.setTokenEnhancers( Arrays.asList(
                accessTokenConverter ) );

        endpoints.tokenStore( tokenStore ).accessTokenConverter(
                accessTokenConverter ).tokenEnhancer( enhancerChain )
                .authenticationManager( authenticationManager );
    }
}
