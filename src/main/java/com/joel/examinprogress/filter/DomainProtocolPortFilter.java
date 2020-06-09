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
package com.joel.examinprogress.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.joel.examinprogress.context.threads.ThreadLocals;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public class DomainProtocolPortFilter extends GenericFilterBean {

    private String formattedDomainProtocal( String protocol ) {

        if ( protocol.indexOf( "HTTPS" ) != -1 ) {
            protocol = "https:";
        }
        else {
            protocol = "http:";
        }
        return protocol;
    }


    @Override
    public void doFilter( ServletRequest request, ServletResponse response,
            FilterChain chain ) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ( HttpServletRequest )request;

        ThreadLocals.protocolThreadLocal.set( formattedDomainProtocal(
                httpServletRequest.getProtocol() ) );

        ThreadLocals.domainThreadLocal.set( httpServletRequest.getServerName() );
        ThreadLocals.portThreadLocal.set( httpServletRequest.getServerPort() );
        chain.doFilter( request, response );
    }
}
