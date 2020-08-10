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
package com.joel.examinprogress.helper.link;

import org.springframework.stereotype.Service;

/**
 * @author Joel Mumo
 * @date   8th Aug, 2020
 */
@Service
public class LinkHelperImpl implements LinkHelper {

    private String getFormattedProtocol( String protocol ) {

        StringBuilder link = new StringBuilder();
        link.append( protocol );
        link.append( "//" );
        return link.toString();
    }


    @Override
    public String createDomainLink( String domain, int serverPort, String protocol ) {

        String link = getFormattedProtocol( protocol ) + domain + ":"
                + serverPort;

        return link;
    }

}
