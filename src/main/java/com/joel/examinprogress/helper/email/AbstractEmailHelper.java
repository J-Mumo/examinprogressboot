package com.joel.examinprogress.helper.email;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.helper.date.DateHelper;
import com.joel.examinprogress.helper.hash.HashHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class AbstractEmailHelper {

    private Logger logger = LoggerFactory.getLogger( AbstractEmailHelper.class );
    protected SimpleMailMessage doNotReplyTemplateMessage;

    @Autowired
    protected JavaMailSender javaMailSender;

    protected Configuration configuration;

    @Autowired
    protected MessageSource resourceBundleMessageSource;

    @Autowired
    protected HashHelper hashHelper;

    @Value( "${email.disabled}" )
    protected String emailDisabled;

    @Value( "${email.do.not.reply}" )
    protected String emailDoNotReplyTo;

    //    @Value( "${hostname}" )
    //    protected String hostName;

    protected FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean;

    @Autowired
    protected AbstractEmailHelper(
            FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean,
            SimpleMailMessage doNotReplyTemplateMessage ) {

        this.freeMarkerConfigurationFactoryBean = freeMarkerConfigurationFactoryBean;
        this.doNotReplyTemplateMessage = doNotReplyTemplateMessage;

        try {
            this.configuration = this.freeMarkerConfigurationFactoryBean.createConfiguration();
            this.doNotReplyTemplateMessage.setFrom( emailDoNotReplyTo );
        }
        catch ( IOException | TemplateException e ) {

            throw new MailPreparationException(
                    "A FreeMarker error occurred creating configuration", e );
        }

        this.doNotReplyTemplateMessage = doNotReplyTemplateMessage;

        try {
            this.configuration = freeMarkerConfigurationFactoryBean.createConfiguration();
            doNotReplyTemplateMessage.setFrom( emailDoNotReplyTo );
        }
        catch ( TemplateException | IOException e ) {

            throw new MailPreparationException(
                    "A FreeMarker error occurred creating configuration", e );
        }
    }


    protected void sendMessageInThread( final SimpleMailMessage templateMessage,
            final Map<String, Object> model, final Template freemarkerTemplate,
            String fromEmail, File file, List<File> attachments ) {

        logger.debug( "Spinning off sending email in seperate thread using "
                + "freemarker template :" + freemarkerTemplate.getName() );

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                try {
                    sendMessage( templateMessage, model, fromEmail,
                            freemarkerTemplate, file, attachments );
                }
                catch ( Exception e ) {

                    logger.error( "Error sending email using freemarker template, "
                            + freemarkerTemplate.getName(), e );

                    throw new RuntimeException( "Error sending email using freemarker template",
                            e );
                }
            }
        };

        Thread thread = new Thread( runnable );
        thread.start();
    }


    protected void sendMessageInThread( final SimpleMailMessage templateMessage,
            final Map<String, Object> model, final Template freemarkerTemplate,
            File file ) {

        logger.debug( "Spinning off sending email in seperate thread using "
                + "freemarker template :" + freemarkerTemplate.getName() );

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                try {
                    sendMessage( templateMessage, model, freemarkerTemplate,
                            file );
                }
                catch ( Exception e ) {

                    logger.error(
                            "Error sending email using freemarker template, "
                                    + freemarkerTemplate.getName(), e );

                    throw new RuntimeException(
                            "Error sending email using freemarker template",
                            e );
                }
            }
        };

        Thread thread = new Thread( runnable );
        thread.start();
    }


    private void addAttachements( List<File> attachments, MimeMessageHelper helper ) {

        if ( attachments != null ) {

            if ( attachments.size() > 0 ) {

                for ( File attachment : attachments ) {

                    if ( attachment.exists() ) {

                        try {
                            helper.addAttachment( attachment.getName(), attachment );
                        }
                        catch ( MessagingException e ) {
                            throw new RuntimeException( "Failed to attachments",
                                    e );
                        }
                    }
                }
            }
        }
    }


    protected void sendMessage( final SimpleMailMessage templateMessage,
            final Map<String, Object> model, final Template freemarkerTemplate,
            File emailLogoFile ) {

        if ( emailDisabled != null && "true".equals( emailDisabled ) ) {

            logger.debug( "Email not sent because email sending disabled "
                    + "with property email.disabled" );

            return;
        }

        javaMailSender.send( new MimeMessagePreparator() {

            @Override
            public void prepare( MimeMessage mimeMessage )
                    throws MessagingException {

                try {

                    MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
                    MimeMailMessage mimeMailMessage = new MimeMailMessage( helper );
                    templateMessage.copyTo( mimeMailMessage );
                    helper.setSubject( ( String )model.get( "subject" ) );
                    helper.setFrom( emailDoNotReplyTo );

                    model.put( "img", "none" );
                    model.put( "alt", "Company Logo" );
                    model.put( "title", "Company Logo" );

                    if ( emailLogoFile != null ) {

                        if ( emailLogoFile.exists() ) {

                            model.put( "img", "img" );
                        }
                    }

                    helper.setFrom( emailDoNotReplyTo );

                    String text =
                            FreeMarkerTemplateUtils.processTemplateIntoString(
                                    freemarkerTemplate, model );

                    logger.debug( "Free marker email template" + text );
                    helper.setText( text, true );

                    if ( emailLogoFile != null ) {

                        if ( emailLogoFile.exists() ) {

                            helper.addInline( "logoemail01", emailLogoFile );
                        }
                    }

                }
                catch ( IOException e ) {

                    throw new MailPreparationException(
                            "An I/O error occurred during composition "
                                    + "of email", e );
                }
                catch ( TemplateException e ) {

                    throw new MailPreparationException(
                            "A FreeMarker error occurred during "
                                    + "composition of email", e );
                }
            }
        } );
    }


    protected void sendMessage( final SimpleMailMessage templateMessage,
            final Map<String, Object> model, String fromEmail,
            final Template freemarkerTemplate,
            File emailLogoFile, List<File> attachments ) {

        if ( emailDisabled != null && "true".equals( emailDisabled ) ) {

            logger.debug( "Email not sent because email sending disabled "
                    + "with property email.disabled" );

            return;
        }

        javaMailSender.send( new MimeMessagePreparator() {

            @Override
            public void prepare( MimeMessage mimeMessage )
                    throws MessagingException {

                try {

                    MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
                    MimeMailMessage mimeMailMessage = new MimeMailMessage( helper );
                    templateMessage.copyTo( mimeMailMessage );
                    helper.setSubject( ( String )model.get( "subject" ) );
                    helper.setFrom( emailDoNotReplyTo );
                    model.put( "img", "none" );

                    if ( emailLogoFile != null ) {

                        if ( emailLogoFile.exists() ) {

                            model.put( "img", "img" );
                        }
                    }

                    String text =
                            FreeMarkerTemplateUtils.processTemplateIntoString( freemarkerTemplate,
                                    model );

                    helper.setText( text, true );

                    /**
                     * String staticServeApacheDir =
                     * propertyHelper.getPropertyValue(
                     * PropertyHelper.STATIC_SERVE_APACHE_DIR );
                     *
                     * String inapesaMailLogoPath = staticServeApacheDir +
                     * "/images/emaillogo.png";
                     **/

                    if ( emailLogoFile != null ) {

                        if ( emailLogoFile.exists() ) {

                            helper.addInline( "logoemail01", emailLogoFile );
                        }
                    }

                    addAttachements( attachments, helper );

                }

                catch ( IOException e ) {
                    throw new MailPreparationException(
                            "An I/O error occurred during composition "
                                    + "of email", e );
                }
                catch ( TemplateException e ) {

                    throw new MailPreparationException(
                            "A FreeMarker error occurred during "
                                    + "composition of email", e );
                }
            }
        } );
    }


    protected void createDateModel( Map<String, Object> model, Locale locale ) {

        String letterDate = DateHelper.FORMAT_EEEEE_dd_MMMM_yyyy_HH_mm.format( new Date() );
        model.put( "date", letterDate );
    }


    protected void createFooterModel( Map<String, Object> model,
            DomainOrganisation organisation, Locale locale ) {

        String management = resourceBundleMessageSource.getMessage(
                "EmailHelperImpl.management", new Object[] {}, locale );

        String formattedManagement = MessageFormat.format( management,
                organisation.getName() );

        model.put( "management", formattedManagement );
        model.put( "alt", organisation.getName() );
        model.put( "title", organisation.getName() );
    }
}
