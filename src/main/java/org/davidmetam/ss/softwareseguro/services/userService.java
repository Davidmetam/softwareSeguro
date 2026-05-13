package org.davidmetam.ss.softwareseguro.services;

public interface userService {
    String registerUser(String username, String password);
    boolean loginVulnerable(String username, String password);
    boolean loginSeguro(String username, String password);
}
