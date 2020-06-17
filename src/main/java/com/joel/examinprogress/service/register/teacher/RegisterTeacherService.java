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
package com.joel.examinprogress.service.register.teacher;

import java.io.IOException;

import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   28 May, 2020
 */
public interface RegisterTeacherService {

    String EMAIL_ERROR_RBKEY = "boot/register/error/email/email_exists_already";

    SaveResponse save( RegisterTeacherRequest request, String domain, int serverPort, String protocol )
            throws IOException;

}
