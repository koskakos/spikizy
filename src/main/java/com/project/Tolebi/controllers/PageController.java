package com.project.Tolebi.controllers;

import com.project.Tolebi.helpers.CloudinaryDB;
import com.project.Tolebi.helpers.EMailSender;
//import com.project.Tolebi.helpers.MailSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PageController {

//    private final UserService userService;

    @GetMapping("")
    public String mainPage() {
//        mailSender.sendSimpleMail("fafsdfas93@mail.ru", "fadgda", "fadfadgad");
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) return "index_authorized";
        else return "index";
    }

//    @GetMapping("da")
//    public String da() {
//        System.out.println(mailSender.sendSimpleMail("fafsdfas93@mail.ru", "fadgad", "adfagad"));
//        return "redirect:/";
//    }

//    @GetMapping("authorized")
//    public String authorizedMainPage() {
//        return "index_authorized";
//    }

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
