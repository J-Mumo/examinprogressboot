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
package com.joel.examinprogress.repository.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.address.AddressType;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface AddressTypeRepository extends CrudRepository<AddressType,
        Long> {

    @Override
    Optional<AddressType> findById( Long addressTypeId );


    List<AddressType> findByName( String name );
}
