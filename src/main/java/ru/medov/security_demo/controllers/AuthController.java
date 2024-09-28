package ru.medov.security_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/success")
    public String successPage(){
        return "success";
    }
}
