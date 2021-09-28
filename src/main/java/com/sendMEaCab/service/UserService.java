package com.sendMEaCab.service;

import com.sendMEaCab.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(UUID userId);

    User findByUsername(String username);

    List<User> findAllUsers();
}
