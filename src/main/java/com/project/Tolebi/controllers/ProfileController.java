package com.project.Tolebi.controllers;

import com.project.Tolebi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("")
    public String redirect() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
            return "redirect:/";
        else {
            return "redirect:/profile/" + userService.getAuthenticatedId();
        }
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id) {
        if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) return "redirect:/";
        if(id != userService.getAuthenticatedId()) return "redirect:/profile";
        return "profile";
    }

    @PostMapping(path ="/uploadimg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam("img") MultipartFile file) {
        Long id = userService.getAuthenticatedId();
        String url = userService.uploadImage(file);
        userService.destroyUserAvatar(id);
        userService.addUrlImageToUser(url, userService.getUserById(id));
        return "redirect:/profile/" + id;
    }

}
