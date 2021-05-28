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
package com.joel.examinprogress.repository.contact;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.contact.MailingList;

/**
 * @author Joel Mumo
 * @date   May 3, 2021
 */
public interface MailingListRepository extends CrudRepository<MailingList, Long> {

    MailingList findByEmail( String email );
}
