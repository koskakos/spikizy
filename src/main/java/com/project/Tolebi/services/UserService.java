package com.project.Tolebi.services;

import com.project.Tolebi.helpers.CloudinaryDB;
import com.project.Tolebi.helpers.EMailSender;
import com.project.Tolebi.helpers.PasswordTokenGenerator;
import com.project.Tolebi.models.User;
import com.project.Tolebi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EMailSender eMailSender;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User getUserByPasswordToken(String token) {
        return userRepository.findUserByPasswordToken(token);
    }
    public User getUserByAvatarUrl(String url) {
        return userRepository.findUserByAvatarUrl(url);
    }
    public boolean checkPassword(Long id, String password) {
        User user = getUserById(id);
        if(user == null) return false;
        return passwordEncoder.matches(password, user.getPassword());
    }
    public boolean addUser(User user) {
        // Надо сделать проверку на уникальность
//        if(userRepository.findUserByEmail(user.getEmail()) != null) return false;
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
//        userRepository.deleteById(user.getId());
        user.setPassword(password);
        addUser(user);
        return true;
    }

    public void forgotPassword(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user != null){
            eMailSender.sendMail(email, "Spikizy password Recovery", "Your password recovery link http://localhost:8080/request/" + user.getPasswordToken());
        }
    }
    public void destroyUserAvatar(Long id) {
        String url = getUserById(id).getAvatarUrl();
        if(url == null) return;
        String publicId = url.substring(url.length() - 40, url.length());
        CloudinaryDB.destroy(publicId);
    }
    public String uploadImage(MultipartFile file) {
        String t = PasswordTokenGenerator.generate();
        String url = "http://res.cloudinary.com/dxuquaaez/image/upload/" + t;
        while(getUserByAvatarUrl(url) != null) {
            t = PasswordTokenGenerator.generate();
            url = "http://res.cloudinary.com/dxuquaaez/image/upload/" + t;
        }
        return CloudinaryDB.upload(file, t);
    }

    public void addUrlImageToUser(String url, User user) {
        user.setAvatarUrl(url);
        userRepository.save(user);
    }

    public Long getAuthenticatedId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        return id;
    }
}
