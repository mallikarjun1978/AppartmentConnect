package com.example.demo.controller;

import com.example.demo.entity.Notifications;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/sendnotification")
    public String showNotificationForm(Model model) {
        model.addAttribute("notification", new Notifications());
        return "sendnotification"; 
    }

 
    @PostMapping("/notifications")
    public String sendNotification(@ModelAttribute("notification") Notifications notification) {
        notificationService.sendNotification(notification);
        return "sendnotification";
    }

    @GetMapping("/resident/notifications")
    public String viewResidentNotifications(Model model) {
        List<Notifications> notifications = notificationService.getAllNotifications(); // or filter by resident
        model.addAttribute("notifications", notifications);
        return "notifications"; 
    }

    @PostMapping("/markAsRead/{id}")
    public String markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return "notifications";
}
}
