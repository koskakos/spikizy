package com.project.Tolebi.controllers;

import com.project.Tolebi.models.User;
import com.project.Tolebi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public String createUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = new User();

        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setEmail(email);
        userService.addUser(user);

        model.addAttribute("user", user);
        return "redirect:/";
    }
}
