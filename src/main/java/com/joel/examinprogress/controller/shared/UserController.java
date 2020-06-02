package com.joel.examinprogress.controller.shared;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.context.threads.ThreadLocals;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.helper.loggingin.LoggedInCredentialsHelper;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.repository.user.UserRepository;
import com.joel.examinprogress.service.email.EmailActivationRequest;
import com.joel.examinprogress.service.email.EmailActivationResponse;
import com.joel.examinprogress.service.email.EmailService;
import com.joel.examinprogress.service.email.ForgottenPasswordRequest;
import com.joel.examinprogress.service.forgottenpassword.reset.ResetUserForgottenPasswordService;
import com.joel.examinprogress.service.forgottenpassword.reset.UserResetForgottenPasswordRequest;
import com.joel.examinprogress.service.forgottenpassword.reset.UserVerifyForgottenPasswordCodeRequest;
import com.joel.examinprogress.service.forgottenpassword.reset.VerificationResponse;
import com.joel.examinprogress.service.register.RegisterRequest;
import com.joel.examinprogress.service.register.RegisterService;
import com.joel.examinprogress.service.shared.SaveResponse;
import com.joel.examinprogress.service.shared.loggedindetails.LoggedInUserDetails;
import com.joel.examinprogress.service.shared.loggedindetails.LoggedInUserDetailsService;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@RestController
@RequestMapping( "/examinprogress/user" )
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ResetUserForgottenPasswordService resetUserForgottenPasswordService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoggedInUserDetailsService loggedInUserDetailsService;

    @Autowired
    private LoggedInCredentialsHelper loggedInCredentialsHelper;

    /**
     * call this method when you want to register
     */
    @RequestMapping( value = "register/save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save(
            @RequestBody RegisterRequest registerRequest )
            throws IOException {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();

        try {
            SaveResponse saveRegisterDevResponse = registerService.save(
                    registerRequest, domain, serverPort, protocol );

            return ResponseEntity.status( HttpStatus.OK ).body(
                    saveRegisterDevResponse );
        }
        catch ( IOException e ) {

            throw new RuntimeException( "Problem while registering",
                    e );
        }
    }


    /**
     * Call this method to get the username for the header
     */
    @RequestMapping( value = "/loggedinuserdetails/get",
            method = RequestMethod.POST )

    public ResponseEntity<LoggedInUserDetails> getLoggedInUserDetails() {

        User user = loggedInCredentialsHelper.getLoggedInUser();

        LoggedInUserDetails loggedInDetails = loggedInUserDetailsService
                .getLoggedInUserDetails( user.getId() );

        return ResponseEntity.status( HttpStatus.OK ).body( loggedInDetails );
    }

    // =============================================================================================


    /**
     * Call this to complete activation. When the user clicks on the link in the
     * email it calls the web which in turn calls this
     */
    @RequestMapping( value = "/activate", method = RequestMethod.POST )
    public ResponseEntity<EmailActivationResponse> activateUserEmail(
            @RequestBody String emailActivationCode ) {

        EmailActivationResponse emailActivationResponse = emailService
                .activateUserEmail( emailActivationCode );

        return ResponseEntity.status( HttpStatus.OK ).body(
                emailActivationResponse );
    }


    /**
     * call this method when you want to resend an activation email
     */
    @RequestMapping( value = "/resendactivationemail",
            method = RequestMethod.POST )
    public ResponseEntity<EmailSentResponse> resendActivationEmail(
            @RequestBody EmailActivationRequest emailActivationRequest ) {

        Long userId = loggedInCredentialsHelper.getLoggedInUser().getId();
        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        DomainOrganisation organisation = organisationRepository.findByDomain(
                domain );
        Locale locale = Locale.forLanguageTag( "en" );
        User user = userRepository.findById( userId ).orElse( null );

        EmailSentResponse emailSentResponse = emailService.sendActivationEmail(
                organisation, user, user.getEmail(), locale, domain, serverPort,
                protocol );

        return ResponseEntity.status( HttpStatus.OK ).body( emailSentResponse );
    }

    // =============================================================================================


    /*
     * Sends an email with a forgotten password link in it
     */
    @RequestMapping( value = "/sendforgottenpasswordemail",
            method = RequestMethod.POST )
    public EmailSentResponse sendForgottenPasswordEmail(
            @RequestBody ForgottenPasswordRequest request ) {

        String domain = ThreadLocals.domainThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();
        int serverPort = ThreadLocals.portThreadLocal.get();

        EmailSentResponse emailSentResponse = emailService
                .sendForgottenPasswordEmail( request, domain, serverPort,
                        protocol );

        return emailSentResponse;
    }


    /**
     * call this to check if the code is ok
     */
    @RequestMapping( value = "/verifyforgottenpasswordcode",
            method = RequestMethod.POST )
    public ResponseEntity<VerificationResponse> resetForgottenPassword(
            @RequestBody UserVerifyForgottenPasswordCodeRequest userVerifyForgottenPasswordCodeRequest ) {

        VerificationResponse verificationResponse =
                resetUserForgottenPasswordService
                        .verifyForgottenPasswordResetCode(
                                userVerifyForgottenPasswordCodeRequest );

        return ResponseEntity.status( HttpStatus.CREATED ).body(
                verificationResponse );
    }


    /**
     * Call this to save the new password
     */
    @RequestMapping( value = "/resetforgottenpassword",
            method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> verifyForgottenPasswordResetCode(
            @RequestBody UserResetForgottenPasswordRequest userResetForgottenPasswordRequest ) {

        SaveResponse saveUserResponse = resetUserForgottenPasswordService
                .resetUserForgottenPassword(
                        userResetForgottenPasswordRequest );

        return ResponseEntity.status( HttpStatus.CREATED ).body(
                saveUserResponse );
    }
}
