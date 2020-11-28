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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value( "${security.signing-key}" )
    private String signingKey;

    @Value( "${security.encoding-strength}" )
    private Integer encodingStrength;

    @Value( "${security.security-realm}" )
    private String securityRealm;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure( AuthenticationManagerBuilder auth )
            throws Exception {

        auth.userDetailsService( userDetailsService ).passwordEncoder(
                passwordEncoder() );
    }


    @Override
    public void configure( WebSecurity web ) {

        web.ignoring().antMatchers(
                "/examinprogress/user/register/save",
                "/examinprogress/user/activate",
                "/examinprogress/student/exam/detail/initialdata" );
    }


    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS ).and().authorizeRequests()
                .antMatchers( "/actuator/**", "/api-docs/**" ).permitAll()
                .antMatchers( "/examinprogress/**" ).authenticated().and().httpBasic()
                .realmName( securityRealm ).and().csrf().disable();
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey( signingKey );
        return converter;
    }


    @Bean
    public TokenStore tokenStore() {

        return new JwtTokenStore( accessTokenConverter() );
    }


    @Bean
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore( tokenStore() );
        defaultTokenServices.setAccessTokenValiditySeconds( 60 );
        defaultTokenServices.setSupportRefreshToken( true );
        return defaultTokenServices;
    }
}
