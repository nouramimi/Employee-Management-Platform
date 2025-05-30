package com.leoni.usersmanagementleoni.repository;

import com.leoni.usersmanagementleoni.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}*/
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
