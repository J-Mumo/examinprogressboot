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
package com.joel.examinprogress.service.contact;

import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.contact.ContactQuery;
import com.joel.examinprogress.domain.organisation.DomainOrganisation;
import com.joel.examinprogress.repository.contact.ContactQueryRepository;
import com.joel.examinprogress.repository.organisation.OrganisationRepository;
import com.joel.examinprogress.service.email.EmailService;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   Apr 20, 2021
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactQueryRepository contactQueryRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Transactional
    @Override
    public SaveResponse save( ContactRequest request, String domain, Integer serverPort,
            String protocol, Locale locale ) {

        DomainOrganisation organisation = organisationRepository.findByDomain( domain );
        ContactQuery contactQuery = new ContactQuery();
        contactQuery.setName( request.getName().trim() );
        contactQuery.setEmail( request.getEmail().trim().toLowerCase() );
        contactQuery.setMessage( request.getMessage().trim() );
        contactQueryRepository.save( contactQuery );
        emailService.notifyAdminsOnContactQuery( organisation, contactQuery, locale,
                serverPort, protocol );
        return new SaveResponse( true, null );
    }

}
