package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminDashBoard {

    @GetMapping("/home")
    public String adminHome(Model model) {
        
        return "adminhome";
    }
}


