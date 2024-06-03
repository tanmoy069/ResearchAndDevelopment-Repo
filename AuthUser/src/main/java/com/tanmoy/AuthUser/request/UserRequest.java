package com.tanmoy.AuthUser.request;

import com.tanmoy.AuthUser.enums.UserRole;

public record UserRequest(String firstName, String lastName, String username, String password, String designation,
                          String deptmstcode, String email, boolean enabled, UserRole userRole) {
}
