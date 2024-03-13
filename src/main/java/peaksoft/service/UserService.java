package peaksoft.service;

import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.SignResponse;

public interface UserService {

    SignResponse signIn(SignInRequest signInRequest);
    SignResponse signUp(SignUpRequest signUpRequest);
}
