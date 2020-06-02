package com.joel.examinprogress.service.forgottenpassword;

import com.joel.examinprogress.domain.user.ForgottenPassword;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public interface ForgottenPasswordService {

    ForgottenPassword getForgottenPasswordByEmail( String email );


    void save( ForgottenPassword forgottenPassword );
}
