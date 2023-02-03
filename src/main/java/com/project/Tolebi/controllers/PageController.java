package com.project.Tolebi.controllers;

import com.project.Tolebi.models.User;
import com.project.Tolebi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;

    @GetMapping("")
    public String mainPage() {
        return "index";
    }

    @GetMapping("authorized")
    public String authorizedMainPage() {
        return "index_authorized";
    }

//    @GetMapping("da")
//    public String da() {
//        User user = new User();
//        user.setEmail("adf");
//        user.setName("fadg");
//        user.setPassword("124");
//        user.setSurname("143");
//        user.setEnabled(true);
//        userService.addUser(user);
//        return "redirect:/";
//    }

}
