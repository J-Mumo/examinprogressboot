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
package com.joel.examinprogress.controller.shared;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.service.shared.agora.RtcTokenBuilderService;
import com.joel.examinprogress.service.shared.agora.RtcTokenRequest;
import com.joel.examinprogress.service.shared.agora.RtcTokenResponse;

/**
 * @author Joel Mumo
 * @date   Dec 17, 2020
 */
@RestController
@RequestMapping( "/examinprogress/agora" )
public class AgoraController {

    @Autowired
    private RtcTokenBuilderService rtcTokenBuilderService;

    @RequestMapping( value = "rtctoken", method = RequestMethod.POST )
    public ResponseEntity<RtcTokenResponse> getRtcToken( @RequestBody RtcTokenRequest request )
            throws IOException {

        RtcTokenResponse rtcToken = rtcTokenBuilderService.getRtcToken( request );
        return ResponseEntity.status( HttpStatus.OK ).body( rtcToken );
    }
}
