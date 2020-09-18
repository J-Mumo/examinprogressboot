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
package com.joel.examinprogress.service.register;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.user.Role;
import com.joel.examinprogress.domain.user.RoleEnum;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.hash.HashHelper;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
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
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HashHelper hashHelper;

    @Autowired
    private EmailService emailService;

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


    private String getHashWithBcrypt( Long id, String email ) {

        String hashed = passwordEncoder.encode( id + email );
        return hashed;
    }


    private void registerTeacher( User user ) {

        Teacher teacher = new Teacher();
        teacher.setUser( user );
        teacherRepository.save( teacher );

        Role role = roleRepository.findById( RoleEnum.TEACHER.getId() ).get();
        Set<Role> roles = new HashSet<>();
        roles.add( role );
        user.setRoles( roles );
        userRepository.save( user );
    }


    private void registerStudent( User user, RegisterRequest request ) {

        ExamToken examToken = examTokenRepository.findByToken( request.getCode() );
        if ( examToken == null ) {

            Invite invite = inviteRepository.findByInviteCode( request.getCode() );
            String token = getHashWithBcrypt( invite.getId(), user.getEmail() ).replaceAll( "/",
                    "sL4sh" );

            examToken = new ExamToken();
            examToken.setEmail( user.getEmail() );
            examToken.setToken( token );
            examToken.setInvite( invite );
            examTokenRepository.save( examToken );

        }
        Student student = new Student();
        student.setUser( user );
        studentRepository.save( student );

        examToken.setStudent( student );
        examTokenRepository.save( examToken );

        Role role = roleRepository.findById( RoleEnum.STUDENT.getId() ).get();
        Set<Role> roles = new HashSet<>();
        roles.add( role );
        user.setRoles( roles );
        userRepository.save( user );
    }


    @Transactional
    private User register( RegisterRequest request,
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
        userRepository.save( user );

        return user;
    }


    @Override
    @Transactional
    public SaveResponse save( RegisterRequest request, String domain,
            int serverPort, String protocol ) throws IOException {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        String email = request.getEmail();
        boolean emailExists = emailExists( email );
        SaveResponse saveResponse = null;
        User user;

        if ( emailExists ) {

            if ( request.getCode() != null ) {

                user = userRepository.findByEmail( email );
                registerStudent( user, request );
            }
            else {

                saveResponse = new SaveResponse( false, EMAIL_ERROR_RBKEY );
            }
        }
        else {

            user = register( request,
                    organisation,
                    email );

            if ( request.getCode() != null )
                registerStudent( user, request );
            else
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
