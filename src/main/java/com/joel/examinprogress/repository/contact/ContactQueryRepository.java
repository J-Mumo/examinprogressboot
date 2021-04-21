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

import com.joel.examinprogress.domain.contact.ContactQuery;

/**
 * @author Joel Mumo
 * @date   Apr 20, 2021
 */
public interface ContactQueryRepository extends CrudRepository<ContactQuery, Long> {

}
