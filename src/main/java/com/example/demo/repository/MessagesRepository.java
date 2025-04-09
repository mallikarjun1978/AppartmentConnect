package com.example.demo.repository;

import com.example.demo.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
	List<Messages> findBySenderId(Long senderId);

	List<Messages> findByReceiverId(Long receiverId);

	List<Messages> findTop3ByReceiverIdOrderByCreatedAtDesc(Long receiverId);
	
	List<Messages> findAllByOrderByCreatedAtDesc();
}
