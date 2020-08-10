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
package com.joel.examinprogress.controller.student;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joel.examinprogress.service.student.exam.verifytoken.VerifyExamTokenRequest;
import com.joel.examinprogress.service.student.exam.verifytoken.VerifyExamTokenResponse;
import com.joel.examinprogress.service.student.exam.verifytoken.VerifyExamTokenService;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
@RestController
@RequestMapping( "/examinprogress/student/exam" )
public class StudentExamController {

    @Autowired
    private VerifyExamTokenService verifyExamTokenService;

    @RequestMapping( value = "token/verify", method = RequestMethod.POST )
    public ResponseEntity<VerifyExamTokenResponse> verifyToken(
            @RequestBody VerifyExamTokenRequest request )
            throws IOException {

        VerifyExamTokenResponse response = verifyExamTokenService.verifyToken( request );

        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }

}
