package com.tanmoy.AuthUser.response;

import com.tanmoy.AuthUser.entity.User;
import com.tanmoy.AuthUser.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private boolean enabled;
    private boolean tokenExpired;
    private UserRole userRole;

    public static UserResponse from(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

}
