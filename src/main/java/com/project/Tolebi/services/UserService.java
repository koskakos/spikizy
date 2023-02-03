package com.project.Tolebi.services;

import com.project.Tolebi.models.User;
import com.project.Tolebi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean addUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()) != null) return false;
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
