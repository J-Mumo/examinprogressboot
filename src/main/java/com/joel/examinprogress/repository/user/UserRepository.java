package com.joel.examinprogress.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.user.User;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail( String email );


    User findByEmailActivationCode( String emailActivationCode );
}
