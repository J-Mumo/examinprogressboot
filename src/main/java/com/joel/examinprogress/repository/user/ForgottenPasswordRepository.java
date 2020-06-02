package com.joel.examinprogress.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.user.ForgottenPassword;
import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public interface ForgottenPasswordRepository extends CrudRepository<ForgottenPassword, Long> {

    ForgottenPassword findByUser( User user );


    ForgottenPassword findByUserEmail( String email );


    ForgottenPassword findByUserAndCode( User user, String code );
}
