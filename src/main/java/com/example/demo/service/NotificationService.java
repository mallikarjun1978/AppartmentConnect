package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Notifications;

public interface NotificationService {
	List<Notifications> getAllNotifications();
	Notifications createNotification(Notifications notifications);
	void markAsRead(Integer id);
}
