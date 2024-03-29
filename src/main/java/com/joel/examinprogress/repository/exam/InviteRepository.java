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
package com.joel.examinprogress.repository.exam;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.examinprogress.domain.exam.Invite;

/**
 * @author Joel Mumo
 * @date   26th June, 2020
 */
@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    Set<Invite> findByExamId( Long examId );


    Invite findByInviteCode( String inviteCode );
}
