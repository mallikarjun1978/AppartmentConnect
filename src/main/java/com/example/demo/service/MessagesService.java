package com.example.demo.service;

import com.example.demo.entity.Messages;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MessagesService {
	Messages sendMessage(Messages message);

	List<Messages> getMessagesBySender(Long senderId);

	List<Messages> getMessagesByReceiver(Long receiverId);
}
