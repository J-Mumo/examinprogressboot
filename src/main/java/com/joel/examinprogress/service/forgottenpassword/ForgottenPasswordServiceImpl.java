package com.joel.examinprogress.service.forgottenpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.examinprogress.domain.user.ForgottenPassword;
import com.joel.examinprogress.repository.user.ForgottenPasswordRepository;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
@Service
public class ForgottenPasswordServiceImpl implements ForgottenPasswordService {

    @Autowired
    private ForgottenPasswordRepository forgottenPasswordRepository;

    @Override
    public ForgottenPassword getForgottenPasswordByEmail( String email ) {

        return forgottenPasswordRepository.findByUserEmail( email.toLowerCase() );
    }


    @Override
    public void save( ForgottenPassword forgottenPassword ) {

        forgottenPasswordRepository.save( forgottenPassword );
    }

}
