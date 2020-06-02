package com.joel.examinprogress.service.forgottenpassword.reset;

import com.joel.examinprogress.service.shared.SaveResponse;

/**
 * @author Joel Mumo
 * @date   1st June, 2020
 */
public interface ResetUserForgottenPasswordService {

    SaveResponse resetUserForgottenPassword(
            UserResetForgottenPasswordRequest userResetForgottenPasswordRequest );


    VerificationResponse verifyForgottenPasswordResetCode(
            UserVerifyForgottenPasswordCodeRequest userVerifyForgottenPasswordCodeRequest );
}
