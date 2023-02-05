package com.project.Tolebi.services;

import com.project.Tolebi.helpers.EMailSender;
import com.project.Tolebi.helpers.PasswordTokenGenerator;
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
    private final EMailSender eMailSender;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserByPasswordToken(String token) {
        return userRepository.findUserByPasswordToken(token);
    }

    public boolean addUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()) != null) return false;
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String passwordToken = PasswordTokenGenerator.generate();
        while(userRepository.findUserByPasswordToken(passwordToken) != null) passwordToken = PasswordTokenGenerator.generate();
        user.setPasswordToken(passwordToken);
        userRepository.save(user);
        return true;
    }


    public boolean changePassword(String token, String password) {
        User user = userRepository.findUserByPasswordToken(token);
        if(user == null) return false;
        userRepository.deleteById(user.getId());
        user.setPassword(password);
        addUser(user);
        return true;
    }

    public void forgotPassword(String email) {
        User user = userRepository.findUserByEmail(email);
        eMailSender.sendMail(email, "Spikizy password Recovery", "Your password recovery link http://localhost:8080/request/" + user.getPasswordToken());
    }
}
