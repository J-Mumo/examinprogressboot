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
package com.joel.examinprogress.service.register.teacher;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.Role;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.hash.HashHelper;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.repository.user.RoleRepository;
import com.joel.examinprogress.repository.user.UserRepository;
import com.joel.examinprogress.service.email.EmailService;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   28 May, 2020
 */
@Service
public class RegisterTeacherServiceImpl implements RegisterTeacherService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HashHelper hashHelper;

    @Autowired
    EmailService emailService;

    private boolean emailExists( String email ) {

        User user = userRepository.findByEmail( email );

        if ( user == null ) {

            return false;
        }

        return true;
    }


    private String createEmailActivationCode() {

        return hashHelper.getRandomHash();
    }


    private String getPasswordHash( String password ) {

        return hashHelper.getPasswordHashWithBcrypt( password );
    }


    @Transactional
    private void registerTeacher( User user ) {

        Teacher teacher = new Teacher();
        teacher.setUser( user );
        teacherRepository.save( teacher );
    }


    @Transactional
    private User register( RegisterTeacherRequest request,
            DomainOrganisation domainOrganisation, String email )
            throws IOException {

        User user = new User();
        user.setFirstName( request.getFirstName().trim() );
        user.setLastName( request.getLastName().trim() );
        user.setEmail( email.toLowerCase().trim() );
        user.setEnabled( Boolean.FALSE );
        user.setPasswordHash( getPasswordHash( request
                .getPassword().trim() ) );

        String emailActivationCode = createEmailActivationCode();
        user.setEmailActivationCode( emailActivationCode );
        user.setDomainOrganisation( domainOrganisation );
        Set<Role> roles = user.getRoles();
        roles.clear();
        //        roles.addAll( roleRepository.findAll() );
        userRepository.save( user );

        return user;
    }


    @Override
    @Transactional
    public SaveResponse save( RegisterTeacherRequest request, String domain,
            int serverPort, String protocol ) throws IOException {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        String email = request.getEmail();
        boolean emailExists = emailExists( email );
        SaveResponse saveResponse;

        if ( emailExists ) {

            saveResponse = new SaveResponse( false, EMAIL_ERROR_RBKEY );
        }
        else {

            User user = register( request, organisation,
                    email );
            registerTeacher( user );
            Locale locale = new Locale( "en" );

            EmailSentResponse emailSentResponse =
                    emailService.sendActivationEmail( organisation, user,
                            request.getEmail(), locale,
                            domain, serverPort, protocol );

            if ( !emailSentResponse.isEmailSent() ) {

                throw new RuntimeException( "Problem sending email" );
            }

            saveResponse = new SaveResponse( true, null );
        }

        return saveResponse;
    }
}