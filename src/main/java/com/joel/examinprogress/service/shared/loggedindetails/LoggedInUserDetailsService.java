package com.joel.examinprogress.service.shared.loggedindetails;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface LoggedInUserDetailsService {

    LoggedInUserDetails getLoggedInUserDetails( Long userId );
}
