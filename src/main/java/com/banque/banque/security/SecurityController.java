package com.banque.banque.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SecurityController {

    @GetMapping(value="/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/")
    public String home() {
        return "redirect:/compte";
    }

    @GetMapping(value="/403")
    public String accessDenied() {
        return "403";
    }
    
}