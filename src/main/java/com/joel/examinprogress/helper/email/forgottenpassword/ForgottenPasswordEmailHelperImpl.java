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
package com.joel.examinprogress.helper.email.forgottenpassword;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.ForgottenPassword;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.AbstractEmailHelper;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.service.forgottenpassword.ForgottenPasswordService;

import freemarker.template.Template;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
@Component
public class ForgottenPasswordEmailHelperImpl extends AbstractEmailHelper implements
        ForgottenPasswordEmailHelper {

    @Autowired
    private ForgottenPasswordService forgottenPasswordService;

    private void createSubjectModel( Map<String, Object> model,
            DomainOrganisation domainOrganisation, Locale locale ) {

        String subject = resourceBundleMessageSource.getMessage(
                "ForgottenPasswordEmailHelperImpl.forgotten.password.subject",
                new Object[] {},
                locale );

        String formattedSubject = MessageFormat.format( subject, domainOrganisation.getName() );
        model.put( "subject", formattedSubject );
    }


    private void createBodyModel( Map<String, Object> model,
            DomainOrganisation domainOrganisation, Locale locale, User user ) {

        String dear = resourceBundleMessageSource.getMessage(
                "ForgottenPasswordEmailHelperImpl.dear", new Object[] {}, locale );

        model.put( "dear", dear );
        model.put( "name", user.getFirstName() );

        String body = resourceBundleMessageSource.getMessage(
                "UserForgottenPasswordEmailHelperImpl.forgotten.password.body",
                new Object[] { user.getEmail(), domainOrganisation.getDomain() }, locale );

        String formattedBody = MessageFormat.format( body, domainOrganisation.getName() );
        model.put( "body", formattedBody );
    }


    private void saveUserForgottenPasswordDetails(
            ForgottenPassword forgottenPassword, User user ) {

        if ( forgottenPassword == null ) {
            forgottenPassword = new ForgottenPassword();
            forgottenPassword.setUser( user );
        }

        forgottenPassword.setCode( hashHelper.getRandomHash() );
        forgottenPasswordService.save( forgottenPassword );
    }


    private String getFormattedProtocol( String protocol ) {

        StringBuilder link = new StringBuilder();
        link.append( protocol );
        link.append( "//" );
        return link.toString();
    }


    private void createForgottenPasswordLinkModel( Map<String, Object> model,
            ForgottenPassword forgottenPassword, User user, String contextPath,
            String domain, int serverPort, String protocol ) {

        String code = forgottenPassword.getCode();

        String link = getFormattedProtocol( protocol ) + domain + ":"
                + serverPort + contextPath
                + "?userid=" + user.getId() + "&code=" + code;

        model.put( "link", link );

    }


    protected ForgottenPasswordEmailHelperImpl(
            FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean,
            SimpleMailMessage doNotReplyTemplateMessage ) {

        super( freeMarkerConfigurationFactoryBean, doNotReplyTemplateMessage );
    }


    @Override
    @Transactional
    public EmailSentResponse sendEmailForgottenPassword( DomainOrganisation organisation, User user,
            Locale locale, String contextPath, String domain, int serverPort, String protocol ) {

        String templateName = "forgottenPassword.tfl";
        EmailSentResponse emailSentResponse;

        try {
            Template freeMarkerTemplate = this.configuration.getTemplate(
                    templateName );

            Map<String, Object> model = new HashMap<String, Object>();

            // createDateModel( model, locale );
            createSubjectModel( model, organisation, locale );
            createBodyModel( model, organisation, locale, user );

            ForgottenPassword forgottenPassword = forgottenPasswordService
                    .getForgottenPasswordByEmail( user.getEmail() );

            saveUserForgottenPasswordDetails( forgottenPassword, user );

            createForgottenPasswordLinkModel( model, forgottenPassword, user,
                    contextPath, domain, serverPort, protocol );

            createFooterModel( model, organisation, locale );
            doNotReplyTemplateMessage.setTo( user.getEmail() );

            // File emailLogoFile = getOrganisationLogo( organisation );
            File emailLogoFile = null;

            sendMessageInThread( doNotReplyTemplateMessage, model,
                    freeMarkerTemplate, emailLogoFile );

            emailSentResponse = new EmailSentResponse( Boolean.TRUE, "" );
        }
        catch ( IOException e ) {

            emailSentResponse = new EmailSentResponse( Boolean.FALSE, e
                    .getMessage() );

            throw new MailPreparationException(
                    "An I/O error occurred during composition " + "of email",
                    e );
        }
        return emailSentResponse;
    }

}
