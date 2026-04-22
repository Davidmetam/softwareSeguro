package org.davidmetam.ss.softwareseguro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class userController {

    @GetMapping("login")
    public String login(){
        return "secure-login";
    }

}
