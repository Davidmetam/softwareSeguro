package org.davidmetam.ss.softwareseguro.repositories;

import org.davidmetam.ss.softwareseguro.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserEntity, Long> {
}