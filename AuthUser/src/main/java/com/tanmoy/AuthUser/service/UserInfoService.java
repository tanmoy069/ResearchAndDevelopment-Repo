package com.tanmoy.AuthUser.service;

import com.tanmoy.AuthUser.entity.User;
import com.tanmoy.AuthUser.repository.UserRepository;
import com.tanmoy.AuthUser.security.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetail::new).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
