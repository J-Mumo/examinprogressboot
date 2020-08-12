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
package com.joel.examinprogress.helper.email.sendexaminvite;

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

import com.joel.examinprogress.domain.exam.Invite;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.helper.email.AbstractEmailHelper;
import com.joel.examinprogress.helper.email.EmailSentResponse;

import freemarker.template.Template;

/**
 * @author Joel Mumo
 * @date   31st July, 2020
 */
@Component
public class SendExamInviteEmailHelperImpl extends AbstractEmailHelper implements
        SendExamInviteEmailHelper {

    public SendExamInviteEmailHelperImpl(
            FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean,
            SimpleMailMessage doNotReplyTemplateMessage ) {

        super( freeMarkerConfigurationFactoryBean, doNotReplyTemplateMessage );
    }


    private String createSubject( Locale locale, String messageyTpe ) {

        String subject = null;

        if ( messageyTpe.equals( SEND_EXAM_TOKEN ) ) {

            subject = resourceBundleMessageSource.getMessage(
                    "SendExamInviteEmailHelperImpl.subject",
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

        if ( messageType.equals( SEND_EXAM_TOKEN ) ) {

            body = resourceBundleMessageSource.getMessage(
                    "SendExamInviteEmailHelperImpl.body",
                    new Object[] {}, locale );
        }

        return body;
    }


    private void createBodyModel( Map<String, Object> model, String examToken, Invite invite,
            DomainOrganisation organisation, Locale locale, String messageType ) {

        String hello = resourceBundleMessageSource.getMessage(
                "SendExamInviteEmailHelperImpl.hello", new Object[] {}, locale );

        model.put( "hello", hello );
        String body = createBody( locale, messageType );
        String formattedBody = MessageFormat.format( body, examToken, invite.getExam().getName(),
                invite.getExamStartDate() );
        model.put( "body", formattedBody );
    }


    private String getFormattedProtocol( String protocol ) {

        StringBuilder link = new StringBuilder();
        link.append( protocol );
        link.append( "//" );
        return link.toString();
    }


    private String createExamInviteTokenModel( String examToken, String domain, int serverPort,
            String protocol ) {

        String token = getFormattedProtocol( protocol ) + domain + ":"
                + serverPort + "/student/exam/detail/?token=" + examToken;

        return token;
    }


    @Override
    public EmailSentResponse sendExamInvite( DomainOrganisation organisation, String email,
            String examToken, Invite invite, Locale locale, String domain, int serverPort,
            String protocol ) {

        String messageType = SEND_EXAM_TOKEN;
        String templateName = "sendExamInvite.tfl";
        EmailSentResponse emailSentResponse;
        String token = createExamInviteTokenModel( examToken, domain,
                serverPort, protocol );

        try {
            Template freeMarkerTemplate = this.configuration.getTemplate(
                    templateName );

            Map<String, Object> model = new HashMap<String, Object>();
            // createDateModel( model, locale );
            createSubjectModel( model, organisation, locale, messageType );
            createBodyModel( model, token, invite, organisation, locale, messageType );
            createFooterModel( model, organisation, locale );
            doNotReplyTemplateMessage.setTo( email );
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
