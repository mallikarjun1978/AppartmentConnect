package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Notifications;
import com.example.demo.service.NotificationService;

@Controller
@RequestMapping("/resident/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping()
    public String getDashboard(Model model) {
        List<Notifications> notifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @PostMapping("/markAsRead/{id}")
    public String markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return "notifications";
    }
}





