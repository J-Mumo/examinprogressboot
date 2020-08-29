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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Joel Mumo
 * @date   29th Aug, 2020
 */
@Component
public class TimeHelperImpl implements TimeHelper {

    @Value( "${question.network.latency.request.and.response.combined.seconds}" )
    private String networkLatencySecondsStr;

    @Override
    public Integer getRemainingTimeInSeconds( Calendar startTime, Integer maxTimeSeconds ) {

        Calendar endTime = ( Calendar )startTime.clone();
        endTime.add( Calendar.SECOND, maxTimeSeconds );
        Calendar now = Calendar.getInstance();
        Long timeInMilli = endTime.getTimeInMillis() - now.getTimeInMillis();

        if ( timeInMilli < 0 ) {

            timeInMilli = 0l;
        }

        Long timeInSeconds = timeInMilli / 1000;
        return timeInSeconds.intValue();
    }


    @Override
    public Boolean hasExpired( Calendar startTime, Integer maxTimeSeconds ) {

        Integer remainingTimeInSeconds = getRemainingTimeInSeconds( startTime, maxTimeSeconds );
        Integer networkLatencySeconds = Integer.parseInt( networkLatencySecondsStr );

        // networkLatencySeconds is to make up for network latency
        if ( ( remainingTimeInSeconds + networkLatencySeconds ) < 0 ) {

            return true;
        }

        return false;
    }
}
