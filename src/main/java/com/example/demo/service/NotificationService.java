package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Notifications;

public interface NotificationService {
	List<Notifications> getAllNotifications();
	Notifications sendNotification(Notifications notifications);
	List<Notifications>getLast5NOtifications();
	void markAsRead(Integer id);
}