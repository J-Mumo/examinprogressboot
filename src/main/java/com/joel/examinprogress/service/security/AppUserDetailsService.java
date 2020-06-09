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
package com.joel.examinprogress.service.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.joel.examinprogress.domain.user.Role;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.repository.user.UserRepository;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String email )
            throws UsernameNotFoundException {

        if ( email == null || email.trim().equals( "" ) ) {

            throw new UsernameNotFoundException( "Email is null or empty" );
        }

        String emailLower = email.toLowerCase().trim();
        User user = userRepository.findByEmail( emailLower );

        if ( user == null ) {

            throw new UsernameNotFoundException(
                    String.format( "The username %s doesn't exist", emailLower ) );
        }

        Set<Role> roles;

        if ( user.getEnabled() == false ) {

            roles = new HashSet<>();
        }
        else {

            roles = user.getRoles();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for ( Role role : roles ) {

            authorities.add( new SimpleGrantedAuthority( role.getName() ) );

        }

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        emailLower, user.getPasswordHash(), authorities );

        return userDetails;
    }
}
