package org.davidmetam.ss.softwareseguro.services.impl;

import org.davidmetam.ss.softwareseguro.models.UserEntity;
import org.davidmetam.ss.softwareseguro.repositories.userRepository;
import org.davidmetam.ss.softwareseguro.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String registerUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswordPlain(password);
        user.setPasswordHash(encoder.encode(password));
        repository.save(user);
        return "redirect:/login?registered";
    }

    @Override
    public boolean loginVulnerable(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password_plain = '" + password + "'";

        try {
            Query query = entityManager.createNativeQuery(sql, UserEntity.class);
            List<UserEntity> results = query.getResultList();
            return !results.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean loginSeguro(String username, String password) {
        Optional<UserEntity> userOpt = repository.findByUsername(username);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            return encoder.matches(password, user.getPasswordHash());
        }

        return false;
    }
}