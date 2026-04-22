package org.davidmetam.ss.softwareseguro.controllers;

import org.davidmetam.ss.softwareseguro.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class userController {

    @Autowired
    private userService service;

    @GetMapping("login")
    public String login(){
        return "secure-login";
    }

    @PostMapping("/register")
    public String register(){
        return service.registerUser();
    }

}
