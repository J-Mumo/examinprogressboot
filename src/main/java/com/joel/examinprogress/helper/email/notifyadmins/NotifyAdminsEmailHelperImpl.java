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
package com.joel.examinprogress.helper.email.notifyadmins;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.joel.examinprogress.domain.contact.ContactQuery;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.domain.user.RoleEnum;
import com.joel.examinprogress.domain.user.User;
import com.joel.examinprogress.helper.email.AbstractEmailHelper;
import com.joel.examinprogress.helper.email.EmailSentResponse;
import com.joel.examinprogress.repository.user.RoleRepository;
import com.joel.examinprogress.repository.user.UserRepository;

import freemarker.template.Template;

/**
 * @author Joel Mumo
 * @date   Apr 21, 2021
 */
@Component
public class NotifyAdminsEmailHelperImpl extends AbstractEmailHelper implements
        NotifyAdminsEmailHelper {

    /**
     * @param freeMarkerConfigurationFactoryBean
     * @param doNotReplyTemplateMessage
     */
    protected NotifyAdminsEmailHelperImpl(
            FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean,
            SimpleMailMessage doNotReplyTemplateMessage ) {

        super( freeMarkerConfigurationFactoryBean, doNotReplyTemplateMessage );
    }

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private String createSubject( Locale locale ) {

        String subject = resourceBundleMessageSource.getMessage(
                "NotifyAdminsEmailHelperImpl.subject",
                new Object[] {}, locale );

        return subject;
    }


    private void createSubjectModel( Map<String, Object> model,
            DomainOrganisation organisation, Locale locale ) {

        String subject = createSubject( locale );
        String formattedSubject = MessageFormat.format( subject, organisation.getName() );
        model.put( "subject", formattedSubject );
    }


    private String createBody( Locale locale ) {

        String body = resourceBundleMessageSource.getMessage(
                "NotifyAdminsEmailHelperImpl.body",
                new Object[] {}, locale );

        return body;
    }


    private void createBodyModel( Map<String, Object> model, ContactQuery contactQuery,
            DomainOrganisation organisation, Locale locale ) {

        String hello = resourceBundleMessageSource.getMessage(
                "NotifyAdminsEmailHelperImpl.hello", new Object[] {}, locale );

        model.put( "hello", hello );
        String body = createBody( locale );
        String name = contactQuery.getName();
        String email = contactQuery.getEmail();
        String message = contactQuery.getMessage();
        String formattedBody = MessageFormat.format( body, name, email, message );

        model.put( "body", formattedBody );
    }


    @Override
    public EmailSentResponse notifyAdminsOnContactQuery( DomainOrganisation organisation,
            ContactQuery contactQuery, Locale locale, int serverPort,
            String protocol ) {

        String templateName = "notifyAdminsOnContactQuery.tfl";
        EmailSentResponse emailSentResponse;
        Long roleId = roleRepository.findById( RoleEnum.ADMIN.getId() ).get().getId();
        Set<User> users = userRepository.findByRolesIdIn( new Long[] { roleId } );

        for ( User user : users ) {
            try {
                Template freeMarkerTemplate = this.configuration.getTemplate( templateName );
                Map<String, Object> model = new HashMap<String, Object>();
                createSubjectModel( model, organisation, locale );
                createBodyModel( model, contactQuery, organisation, locale );
                createFooterModel( model, organisation, locale );
                doNotReplyTemplateMessage.setTo( user.getEmail() );
                File emailLogoFile = null;

                sendMessageInThread( doNotReplyTemplateMessage, model,
                        freeMarkerTemplate, emailLogoFile );

                emailSentResponse = new EmailSentResponse( Boolean.TRUE, "" );
            }
            catch ( IOException e ) {

                emailSentResponse = new EmailSentResponse( Boolean.FALSE, e.getMessage() );
                throw new MailPreparationException(
                        "An I/O error occurred during composition " + "of email",
                        e );
            }
        }

        emailSentResponse = new EmailSentResponse( Boolean.TRUE, "" );
        return emailSentResponse;
    }

}
