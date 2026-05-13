package org.davidmetam.ss.softwareseguro.controllers;

import org.davidmetam.ss.softwareseguro.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String register(@RequestParam String username, @RequestParam String password){
        return service.registerUser(username, password);
    }

    @PostMapping("/login-vulnerable")
    public String loginVulnerable(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = service.loginVulnerable(username, password);

        if (isAuthenticated) {
            return "redirect:/login?successV";
        } else {
            model.addAttribute("errorV", "Usuario o password incorrectos (Inseguro)");
            return "secure-login";
        }
    }

    @PostMapping("/login-seguro")
    public String loginSeguro(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = service.loginSeguro(username, password);

        if (isAuthenticated) {
            return "redirect:/login?successS";
        } else {
            model.addAttribute("errorS", "Credenciales inválidas o intento de inyección bloqueado");
            return "secure-login";
        }
    }
}