package com.joel.examinprogress.domain.user;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public enum RoleEnum {

    ADMIN( 1l ),
    EMAIL_VALIDATED( 2l ),
    DEV( 4l );

    private Long id;

    private RoleEnum( Long id ) {

        this.id = id;
    }


    public Long getId() {

        return id;
    }
}
