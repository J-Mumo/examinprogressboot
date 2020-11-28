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
package com.joel.examinprogress.service.teacher.exam.invite.send;

import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.exam.Exam;
import com.joel.examinprogress.domain.exam.ExamToken;
import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.student.Student;
import com.joel.examinprogress.domain.teacher.Teacher;
import com.joel.examinprogress.domain.token.TokenConsumed;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.link.LinkHelper;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.exam.ExamTokenRepository;
import com.joel.examinprogress.repository.exam.InviteRepository;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.student.StudentRepository;
import com.joel.examinprogress.repository.teacher.TeacherRepository;
import com.joel.examinprogress.repository.token.TokenConsumedRepository;
import com.joel.examinprogress.repository.user.UserRepository;
import com.joel.examinprogress.service.email.EmailService;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   25th July, 2020
 */
@Service
public class SendInviteServiceImp implements SendInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ExamTokenRepository examTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LinkHelper linkHelper;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenConsumedRepository tokenConsumedRepository;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    private boolean emailExists( Set<ExamToken> examTokens, String email ) {

        boolean exists = false;
        for ( ExamToken examToken : examTokens ) {
            if ( email.equals( examToken.getEmail() ) )
                exists = true;
            break;
        }

        return exists;
    }


    private String getHashWithBcrypt( Long id, String email ) {

        String hashed = passwordEncoder.encode( id + email );
        return hashed;
    }


    private void updateTokenConsumption( String email, Exam exam ) {

        User user = loggedInCredentialsHelper.getLoggedInUser();
        Teacher teacher = teacherRepository.findByUser( user );
        User studentUser = userRepository.findByEmail( email );
        Student student = studentUser != null ? studentRepository.findByUser( studentUser ) : null;
        TokenConsumed tokenConsumed = new TokenConsumed();
        tokenConsumed.setEmail( email );
        tokenConsumed.setStudent( student );
        tokenConsumed.setTeacher( teacher );
        tokenConsumed.setExam( exam );
        tokenConsumedRepository.save( tokenConsumed );
        teacher.setTokens( teacher.getTokens() - 1 );
        teacherRepository.save( teacher );
    }


    @Override
    public SendInviteInitialData getInitialData( Long inviteId, String domain,
            Integer serverPort, String protocol ) {

        Invite invite = inviteRepository.findById( inviteId ).get();
        String inviteCode = linkHelper.createDomainLink( domain, serverPort, protocol ) +
                "/student/exam/detail?invitecode=" + invite.getInviteCode();

        return new SendInviteInitialData( inviteCode );
    }


    @Transactional
    @Override
    public SaveResponse sendInviteToEmail( SendInviteToEmailRequest request, String domain,
            Integer serverPort, String protocol ) {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        Exam exam = invite.getExam();
        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );
        String email = request.getEmail();
        boolean emailExists = emailExists( examTokens, email );
        SaveResponse saveResponse;

        if ( emailExists ) {

            saveResponse = new SaveResponse( false, EMAIL_ERROR_RBKEY );
        }
        else {

            String hash = getHashWithBcrypt( invite.getId(), email );
            String linkHash = hash.replaceAll( "/", "sL4sh" );
            String token = linkHash;

            ExamToken examToken = new ExamToken();
            examToken.setEmail( request.getEmail() );
            examToken.setToken( token );
            examToken.setInvite( invite );
            examTokenRepository.save( examToken );
            updateTokenConsumption( email, exam );
            Locale locale = new Locale( "en" );

            EmailSentResponse emailSentResponse =
                    emailService.sendInviteToExam( organisation, email, token, invite, locale,
                            domain, serverPort, protocol );

            if ( !emailSentResponse.isEmailSent() ) {

                return new SaveResponse( false, "Problem sending email" );
            }

            saveResponse = new SaveResponse( true, null );
        }
        return saveResponse;
    }



    @Transactional
    @Override
    public SaveResponse sendInvite( SendInviteRequest request, String domain, Integer serverPort,
            String protocol ) {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        Exam exam = invite.getExam();
        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );
        for ( String email : request.getEmails() ) {
            boolean emailExists = emailExists( examTokens, email );

            if ( !emailExists ) {

                String hash = getHashWithBcrypt( invite.getId(), email );
                String linkHash = hash.replaceAll( "/", "sL4sh" );
                String token = domain + ":4200/student/exam/" + linkHash;

                ExamToken examToken = new ExamToken();
                examToken.setEmail( email );
                examToken.setToken( token );
                examToken.setInvite( invite );
                examTokenRepository.save( examToken );
                updateTokenConsumption( email, exam );
                Locale locale = new Locale( "en" );

                EmailSentResponse emailSentResponse =
                        emailService.sendInviteToExam( organisation, email, token, invite, locale,
                                domain, serverPort, protocol );

                if ( !emailSentResponse.isEmailSent() ) {

                    return new SaveResponse( false, "Problem sending email" );
                }

            }
        }
        return new SaveResponse( true, null );
    }

}
