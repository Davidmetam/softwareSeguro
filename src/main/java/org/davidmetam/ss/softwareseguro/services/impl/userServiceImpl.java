package org.davidmetam.ss.softwareseguro.services.impl;

import org.davidmetam.ss.softwareseguro.models.UserEntity;
import org.davidmetam.ss.softwareseguro.repositories.userRepository;
import org.davidmetam.ss.softwareseguro.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public String registerUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswordPlain(password);
        user.setPasswordHash(encoder.encode(password));

        repository.save(user);

        return "redirect:/login?success";
    }
}