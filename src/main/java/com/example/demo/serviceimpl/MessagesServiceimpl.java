package com.example.demo.serviceimpl;

import com.example.demo.entity.Messages;
import com.example.demo.repository.MessagesRepository;
import com.example.demo.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceimpl implements MessagesService {

	private final MessagesRepository messagesRepository;

	@Autowired
	public MessagesServiceimpl(MessagesRepository messagesRepository) {
		this.messagesRepository = messagesRepository;
	}

	@Override
	public Messages sendMessage(Messages message) {
		return messagesRepository.save(message);
	}

	@Override
	public List<Messages> getMessagesBySender(Long senderId) {
		return messagesRepository.findBySenderId(senderId);
	}

	@Override
	public List<Messages> getMessagesByReceiver(Long receiverId) {
		return messagesRepository.findByReceiverId(receiverId);
	}
}
