package com.joel.examinprogress.helper.email.sendactivationlink;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.AbstractEmailHelper;
import com.joel.examinprogress.helper.email.EmailSentResponse;

import freemarker.template.Template;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Component
public class SendActivationLinkEmailHelperImpl extends AbstractEmailHelper
        implements
        SendActivationLinkEmailHelper {

    public SendActivationLinkEmailHelperImpl(
            FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean,
            SimpleMailMessage doNotReplyTemplateMessage ) {

        super( freeMarkerConfigurationFactoryBean, doNotReplyTemplateMessage );
    }


    private String createSubject( Locale locale, String messageyTpe ) {

        String subject = null;

        if ( messageyTpe.equals( SEND_ACTIVATION_LINK ) ) {

            subject = resourceBundleMessageSource.getMessage(
                    "SendActivationLinkEmailHelperImpl.subject",
                    new Object[] {}, locale );
        }

        return subject;
    }


    private void createSubjectModel( Map<String, Object> model,
            DomainOrganisation organisation, Locale locale,
            String messageType ) {

        String subject = createSubject( locale, messageType );
        String formattedSubject = MessageFormat.format( subject, organisation
                .getName() );
        model.put( "subject", formattedSubject );
    }


    private String createBody( Locale locale, String messageType ) {

        String body = null;

        if ( messageType.equals( SEND_ACTIVATION_LINK ) ) {

            body = resourceBundleMessageSource.getMessage(
                    "SendActivationLinkEmailHelperImpl.body",
                    new Object[] {}, locale );
        }

        return body;
    }


    private void createBodyModel( Map<String, Object> model,
            DomainOrganisation organisation, Locale locale, User user,
            String messageType ) {

        String dear = resourceBundleMessageSource.getMessage(
                "SendActivationLinkEmailHelperImpl.dear", new Object[] {},
                locale );

        model.put( "dear", dear );
        model.put( "name", user.getFirstName() );
        String body = createBody( locale, messageType );
        String formattedBody = MessageFormat.format( body, organisation
                .getName() );
        model.put( "body", formattedBody );
    }


    private String getFormattedProtocol( String protocol ) {

        StringBuilder link = new StringBuilder();
        link.append( protocol );
        link.append( "//" );
        return link.toString();
    }


    private void createActivateAccountLinkModel( Map<String, Object> model,
            String emailActivationCode, String domain, int serverPort,
            String protocol ) {

        String link = getFormattedProtocol( protocol ) + domain + ":"
                + serverPort + "/user/activate?code=" + emailActivationCode;

        model.put( "link", link );
    }


    @Override
    public EmailSentResponse sendEmailActivationLink(
            DomainOrganisation organisation,
            User user,
            String emailTo, Locale locale, String domain, int serverPort,
            String protocol ) {

        String messageType = SEND_ACTIVATION_LINK;
        String templateName = "sendActivationLink.tfl";
        EmailSentResponse emailSentResponse;

        try {
            Template freeMarkerTemplate = this.configuration.getTemplate(
                    templateName );

            Map<String, Object> model = new HashMap<String, Object>();
            // createDateModel( model, locale );
            createSubjectModel( model, organisation, locale, messageType );
            createBodyModel( model, organisation, locale, user, messageType );

            createActivateAccountLinkModel( model, user
                    .getEmailActivationCode(), domain,
                    serverPort, protocol );

            createFooterModel( model, organisation, locale );
            doNotReplyTemplateMessage.setTo( emailTo );
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
