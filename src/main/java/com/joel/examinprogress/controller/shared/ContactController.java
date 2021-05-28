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
package com.joel.examinprogress.controller.shared;

import java.util.Locale;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.context.threads.ThreadLocals;
import com.joel.examinprogress.service.contact.ContactRequest;
import com.joel.examinprogress.service.contact.ContactService;
import com.joel.examinprogress.service.mailinglist.MailingListService;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   Apr 21, 2021
 */
@RestController
@RequestMapping( "/examinprogress/contact" )
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private MailingListService mailingListService;

    @RequestMapping( value = "save", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> save( @RequestBody ContactRequest request ) {

        String domain = ThreadLocals.domainThreadLocal.get();
        Integer serverPort = ThreadLocals.portThreadLocal.get();
        String protocol = ThreadLocals.protocolThreadLocal.get();

        SaveResponse response = contactService.save( request, domain, serverPort,
                protocol, Locale.ENGLISH );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }


    @RequestMapping( value = "mailinglist/subscribe", method = RequestMethod.POST )
    public ResponseEntity<SaveResponse> subscribeToMailingList( @RequestBody @Email String email ) {

        SaveResponse response = mailingListService.subscribeToMailingList( email );
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }
}
