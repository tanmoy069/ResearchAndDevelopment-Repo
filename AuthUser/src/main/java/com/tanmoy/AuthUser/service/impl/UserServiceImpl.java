package com.tanmoy.AuthUser.service.impl;

import com.tanmoy.AuthUser.request.UserRequest;
import com.tanmoy.AuthUser.response.UserResponse;
import com.tanmoy.AuthUser.entity.User;
import com.tanmoy.AuthUser.exception.NotFoundException;
import com.tanmoy.AuthUser.message.ExceptionMessage;
import com.tanmoy.AuthUser.repository.UserRepository;
import com.tanmoy.AuthUser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse findById(Long id) {
        return UserResponse.from(findUserById(id));
    }

    @Transactional
    @Override
    public UserResponse saveUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setDesignation(request.designation());
        user.setDeptmstcode(request.deptmstcode());
        user.setEmail(request.email());
        user.setEnabled(request.enabled());
        user.setUserRole(request.userRole());
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new NotFoundException(ExceptionMessage.DUPLICATE_USERNAME_ERROR);
        }

        user = repository.save(user);
        log.info("{} has been created successfully", user);
        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserById(id);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setDesignation(request.designation());
        user.setDeptmstcode(request.deptmstcode());
        user.setEmail(request.email());
        user.setEnabled(request.enabled());
        user.setUserRole(request.userRole());
        user = repository.save(user);
        log.info("{} has been updated successfully", user);
        return UserResponse.from(user);
    }

    @Override
    public void delete(Long id) {
        User user = findUserById(id);
        user.setEnabled(false);
        repository.save(user);
        log.info("{} has been deleted successfully", user);
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
    }

}
