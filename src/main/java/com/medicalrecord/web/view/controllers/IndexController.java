package com.medicalrecord.web.view.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex(Model model) {
        final String welcomeMessage = "Welcome to the Medical Record Service!";
        model.addAttribute("welcome", welcomeMessage);
        return "index";
    }

    @GetMapping("login")
    public String login(Model model) {
        final String welcomeMessage = "Welcome to the Medical Record Service!";
        model.addAttribute("welcome", welcomeMessage);
        return "login";
    }

    @GetMapping("register")
    public String registerUser(Model model) {
        final String welcomeMessage = "Welcome to the Medical Record Service!";
        model.addAttribute("welcome", welcomeMessage);
        return "register";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        final String welcomeMessage = "Welcome to the Medical Record Service!";
        model.addAttribute("welcome", welcomeMessage);
        return "login";
    }

    @GetMapping("unauthorized")
    public String unauthorized(Model model) {
        final String welcomeMessage = "Welcome to the Medical Record Service!";
        model.addAttribute("welcome", welcomeMessage);
        return "unauthorized";
    }
}
