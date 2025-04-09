package com.example.demo.controller;

import com.example.demo.entity.Messages;
import com.example.demo.repository.MessagesRepository;
import com.example.demo.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {
	
	@Autowired
	private MessagesRepository repository;

	private final MessagesService messagesService;

	@Autowired
	public MessagesController(MessagesService messagesService) {
		this.messagesService = messagesService;
	}

	@PostMapping("/send")
	public ResponseEntity<Messages> sendMessage(@RequestBody Messages message) {
		Messages savedMessage = messagesService.sendMessage(message);
		return ResponseEntity.ok(savedMessage);
	}

	@GetMapping("/sender/{senderId}")
	public ResponseEntity<List<Messages>> getMessagesBySender(@PathVariable Long senderId) {
		return ResponseEntity.ok(messagesService.getMessagesBySender(senderId));
	}

	@GetMapping("/receiver/{receiverId}")
	public ResponseEntity<List<Messages>> getMessagesByReceiver(@PathVariable Long receiverId) {
		return ResponseEntity.ok(messagesService.getMessagesByReceiver(receiverId));
	}
	
	@GetMapping("/admin/view")
	public ResponseEntity<List<Messages>> getAllMessages(){
		return ResponseEntity.ok(repository.findAllByOrderByCreatedAtDesc());
	}
	
}
