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
package com.joel.examinprogress.repository.token;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.token.TokenConsumed;

/**
 * @author Joel Mumo
 * @date   Nov 27, 2020
 */
@Repository
public interface TokenConsumedRepository extends CrudRepository<TokenConsumed, Long> {

    Set<TokenConsumed> findByTeacherId( Long teacherId );
}
