package com.example.demo.controller;

import com.example.demo.entity.Messages;
import com.example.demo.entity.Residents;
import com.example.demo.service.MessagesService;
import com.example.demo.service.ResidentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDashBoard {

    @Autowired
    private MessagesService messageService;
    @Autowired
	private ResidentsService residentsService;

    @GetMapping("/adminhome")
    public ModelAndView adminHome() {
        // Get the last 3 unread messages
        List<Messages> Messages = messageService.getMessagesByReceiver(1l);//messageService.getLastThreeUnreadMessages(1L); // Use correct receiverId
        System.out.println("Fetched Messages: " + Messages);
        
        
        // Create ModelAndView object
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");

        // Add messages to the model
        modelAndView.addObject("Messages", Messages);

        return modelAndView;
    }
        
    @GetMapping("/residentslist")
    public String viewResidentsList(Model model) {
        List<Residents> residents = residentsService.getAllResidents();
        model.addAttribute("residents", residents);
        return "residentslist"; // Match with your HTML filename (residentsList.html)
    }


    }

