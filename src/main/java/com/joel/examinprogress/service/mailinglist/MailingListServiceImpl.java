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
package com.joel.examinprogress.service.mailinglist;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.contact.MailingList;
import com.joel.examinprogress.repository.contact.MailingListRepository;
import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   May 3, 2021
 */
@Service
public class MailingListServiceImpl implements MailingListService {

    @Autowired
    private MailingListRepository mailingListRepository;

    @Override
    @Transactional
    public SaveResponse subscribeToMailingList( String email ) {

        MailingList mailingList = mailingListRepository.findByEmail( email.trim().toLowerCase() );

        if ( mailingList == null ) {
            mailingList = new MailingList();
            mailingList.setEmail( email.trim().toLowerCase() );
            mailingListRepository.save( mailingList );
        }

        return new SaveResponse( true, null );
    }
}
