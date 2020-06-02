package com.joel.examinprogress.repository.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface RoleRepository extends CrudRepository<com.joel.examinprogress.domain.user.Role,
        Long> {

}
