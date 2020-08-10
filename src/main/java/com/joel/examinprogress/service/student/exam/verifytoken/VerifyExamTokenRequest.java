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
package com.joel.examinprogress.service.student.exam.verifytoken;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
public class VerifyExamTokenRequest {

    private boolean inviteCode;
    private String code;

    public VerifyExamTokenRequest( boolean inviteCode, String code ) {

        super();
        this.inviteCode = inviteCode;
        this.code = code;
    }


    public boolean isInviteCode() {

        return inviteCode;
    }


    public void setInviteCode( boolean inviteCode ) {

        this.inviteCode = inviteCode;
    }


    public String getCode() {

        return code;
    }


    public void setCode( String code ) {

        this.code = code;
    }
}
