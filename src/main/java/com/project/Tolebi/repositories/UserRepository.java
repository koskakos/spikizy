package com.project.Tolebi.repositories;

import com.project.Tolebi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String username);

    User findUserById(Long id);

//    User findUserByUsername(String username);
    User findUserByAvatarUrl(String url);
    User findUserByPasswordToken(String passwordToken);

    void deleteUserById(Long id);
}
