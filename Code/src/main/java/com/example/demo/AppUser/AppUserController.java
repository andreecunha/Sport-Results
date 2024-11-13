package com.example.demo.AppUser;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@Controller
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String newUser(Model m) {
        m.addAttribute("user", new AppUser());
        return "register_user";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute AppUser user) {
        this.appUserService.signUpUser(user);
        return "redirect:/";
    }

}
