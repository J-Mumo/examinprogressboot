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

        for ( ExamToken examToken : examTokens ) {
            if ( email.equals( examToken.getEmail() ) )
                return true;
        }

        return false;
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


    private SendInviteResponse createExamTokenAndEmailInvite( Invite invite, String email,
            String domain, Integer serverPort, String protocol ) {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        Exam exam = invite.getExam();
        String hash = getHashWithBcrypt( invite.getId(), email );
        String linkHash = hash.replaceAll( "/", "sL4sh" );
        String token = linkHash;

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

            return new SendInviteResponse( true, true, false, "Problem sending email" );
        }

        return new SendInviteResponse( true, false, false, null );
    }


    private boolean teacherHasTokens( Invite invite ) {

        Teacher teacher = invite.getExam().getTeacher();

        if ( teacher.getTokens() > 0 )
            return true;
        return false;
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
    public SendInviteResponse sendInviteToEmail( SendInviteToEmailRequest request, String domain,
            Integer serverPort, String protocol ) {

        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );
        String email = request.getEmail();
        boolean emailExists = emailExists( examTokens, email );
        SendInviteResponse saveResponse;

        if ( emailExists ) {

            saveResponse = new SendInviteResponse( false, true, false, EMAIL_ERROR_RBKEY );

            saveResponse.getUnsentEmails().add( email );
        }
        else if (teacherHasTokens(invite)) {

            saveResponse = createExamTokenAndEmailInvite( invite, email, domain, serverPort, protocol );
        } else {
            
            saveResponse = new SendInviteResponse( false, false, true,
                    "Error! Cannot send invite. You do not have "
                            + "enough tokens. Please add more tokens to proceed" );

            saveResponse.getUnsentEmails().add( email );
        }
        return saveResponse;
    }


    @Transactional
    @Override
    public SendInviteResponse sendInvite( SendInviteRequest request, String domain,
            Integer serverPort, String protocol ) {

        SendInviteResponse response = new SendInviteResponse();
        Invite invite = inviteRepository.findById( request.getInviteId() ).get();
        Set<ExamToken> examTokens = examTokenRepository.findByInvite( invite );

        for ( String email : request.getEmails() ) {
            boolean emailExist = emailExists( examTokens, email );
            boolean teacherHasTokens = teacherHasTokens( invite );

            if ( teacherHasTokens && !emailExist ) {

                SendInviteResponse inviteResponse = createExamTokenAndEmailInvite( invite, email,
                        domain, serverPort, protocol );

                if ( inviteResponse.isEmailError() ) {

                    response.setEmailError( true );
                    response.getUnsentEmails().add( email );
                }

            }
            else if ( !teacherHasTokens ) {
                response.setTokensError( true );
                response.getUnsentEmails().add( email );
            }
        }

        response.setSent( true );
        return response;
    }

}
