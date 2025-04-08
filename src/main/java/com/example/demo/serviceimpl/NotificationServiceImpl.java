package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Notifications;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Override
	public List<Notifications> getAllNotifications() {
		return notificationRepository.findAll();
	}

	@Override
	public Notifications createNotification(Notifications notification) {
		return notificationRepository.save(notification);
	}

	@Override
	@Transactional
	public void markAsRead(Integer id) {
		Optional<Notifications> notificationOptional = notificationRepository.findById(id);
		if (notificationOptional.isPresent()) {
			Notifications notification = notificationOptional.get();
			notification.setIsRead(true);
			notificationRepository.save(notification);
		} else {
			throw new RuntimeException("Notification not found with id: " + id);
		}
	}
}
