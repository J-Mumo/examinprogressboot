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
package com.joel.examinprogress.service.shared.agora;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.joel.examinprogress.service.shared.agora.agora.media.RtcTokenBuilder;
import com.joel.examinprogress.service.shared.agora.agora.media.RtcTokenBuilder.Role;

/**
 * @author Joel Mumo
 * @date   Dec 17, 2020
 */
@Service
public class RtcTokenBuilderService {

    static String appId = "703bc0bd4c5c4bc99b4172dd0aecc89e";
    static String appCertificate = "7342b2de114e4c4181f6f4c0eb72bb81";
    //    static int uid = 2082341273;
    static int expirationTimeInSeconds = 3600;

    public RtcTokenResponse getRtcToken( RtcTokenRequest request ) {

        String channelName = request.getChannelName();
        boolean student = request.isStudent();
        Long examTokenId = request.getExamTokenId();
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = ( int )( System.currentTimeMillis() / 1000 + expirationTimeInSeconds );
        Random rand = new Random();
        int uid = student ? examTokenId.intValue() : rand.nextInt( 1000000000 );

        String result = token.buildTokenWithUid( appId, appCertificate,
                channelName, uid, Role.Role_Publisher, timestamp );

        return new RtcTokenResponse( result, uid );
    }
}
