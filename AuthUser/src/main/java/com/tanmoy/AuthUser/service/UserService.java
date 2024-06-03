package com.tanmoy.AuthUser.service;

import com.tanmoy.AuthUser.request.UserRequest;
import com.tanmoy.AuthUser.response.UserResponse;

public interface UserService {

    UserResponse findById(Long id);

    UserResponse saveUser(UserRequest request);

    UserResponse updateUser(Long id, UserRequest request);

    void delete(Long id);

}
