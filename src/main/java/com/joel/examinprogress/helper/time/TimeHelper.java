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
package com.joel.examinprogress.helper.time;

import java.util.Calendar;

/**
 * @author Joel Mumo
 * @date   29th Aug, 2020
 */
public interface TimeHelper {

    Integer getRemainingTimeInSeconds( Calendar startTime, Integer maxTimeSeconds );


    Boolean hasExpired( Calendar startTime, Integer maxTimeSeconds );

}
