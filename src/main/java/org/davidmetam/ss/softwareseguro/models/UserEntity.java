package org.davidmetam.ss.softwareseguro.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password_plain", nullable = false)
    private String passwordPlain;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

}