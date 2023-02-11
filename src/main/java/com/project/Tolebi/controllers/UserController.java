package com.project.Tolebi.controllers;

import com.project.Tolebi.models.User;
import com.project.Tolebi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public String createUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("email") String email, @RequestParam("password") String password,
                             @RequestParam("phone") String phone, Model model) {
        User user = new User();

        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userService.addUser(user);

        model.addAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("forgotpassword")
    public String forgotPassword(@RequestParam("email") String email) {
        userService.forgotPassword(email);
        return "redirect:/";
    }

    @PostMapping("request/{token}")
    public String postChangePassword(@PathVariable String token, @RequestParam("Password") String password) {
//        System.out.println(token + " " + password);
//        System.out.println(userService.getUserByPasswordToken("1xusVhDsc4TbkNq0atfo6TCx3oBLMSXdrUdsybXC").getPassword());
//        System.out.println(userService.getUserByPasswordToken(token).getPassword()); //Не находит юзера по токену
        userService.changePassword(token, password);
        return "redirect:/";
    }

    @GetMapping("request/{token}")
    public String changePassword(@PathVariable String token) {

        return "changePassword";
    }
    @ResponseBody
    @PostMapping("checkemail")
    public ResponseEntity checkEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.isUniqueEmail(email));
    }
}
