package com.example.demo.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Notifications;
import com.example.demo.entity.Residents;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.ResidentsRepository;
import com.example.demo.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final ResidentsRepository residentRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository, ResidentsRepository residentRepository) {
		this.notificationRepository = notificationRepository;
		this.residentRepository = residentRepository;
	}

	// ✅ Send the same notification to all residents
	@Override
	public Notifications sendNotification(Notifications notificationTemplate) {
		List<Residents> allResidents = residentRepository.findAll();

		for (Residents resident : allResidents) {
			Notifications notification = new Notifications();
			notification.setTitle(notificationTemplate.getTitle());
			notification.setMessage(notificationTemplate.getMessage());
			
			notification.setTimestamp(LocalDateTime.now());
			notification.setIsRead(false);

			notificationRepository.save(notification);
		}

		
		return notificationTemplate;
	}

	@Override
	public List<Notifications> getAllNotifications() {
		return notificationRepository.findAll();
	}

	@Override
	public List<Notifications> getLast5NOtifications() {
		return notificationRepository.findTop5ByOrderByTimestampDesc();
	}

	@Override
	@Transactional
	public void markAsRead(Integer id) {
		Optional<Notifications> optionalNotification = notificationRepository.findById(id);
		if (optionalNotification.isPresent()) {
			Notifications notification = optionalNotification.get();
			notification.setIsRead(true);
			notificationRepository.save(notification);
		} else {
			throw new RuntimeException("Notification not found with id: " + id);
		}
	}
}
