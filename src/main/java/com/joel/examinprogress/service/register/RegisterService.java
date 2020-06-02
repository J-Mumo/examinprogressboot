package com.joel.examinprogress.service.register;

import java.io.IOException;

import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   28 May, 2020
 */
public interface RegisterService {

    String EMAIL_ERROR_RBKEY = "boot/register/error/email/email_exists_already";

    SaveResponse save( RegisterRequest request, String domain, int serverPort, String protocol )
            throws IOException;

}
