package com.example.demo.controller;

import com.example.demo.entity.Messages;
import com.example.demo.entity.Residents;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping({"/resident/messages", "/admin/messages"})
public class MessagesFrontEndController {

	// view send message form
	@GetMapping("/send")
	public ModelAndView showSendMessagePage(Model model,HttpSession session) {
		Residents resident=(Residents) session.getAttribute("residents");
		System.out.println(resident);
		model.addAttribute("resident",resident);
		return new ModelAndView("sendMessage");
	}

	// send messages
	@PostMapping("/send")
	public ModelAndView sendMessage(@ModelAttribute Messages message, Model model,HttpSession session) {
		Residents resident=(Residents) session.getAttribute("residents");
	    String url = "http://localhost:8090/messages/send";
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.postForObject(url, message, Messages.class);

	    model.addAttribute("resident",resident);
	    model.addAttribute("successMessage", "Message sent successfully!");
	    return new ModelAndView("sendMessage");
	}


	// view sent messages
	@GetMapping("/sender")
	public ModelAndView getMessagesBySender(@RequestParam("senderId") Long senderId, Model model,HttpSession session) {
		Residents resident=(Residents) session.getAttribute("residents");
		String url = "http://localhost:8090/messages/sender/{senderId}";
		RestTemplate restTemplate=new RestTemplate();
		Messages[] messagesArray = restTemplate.getForObject(url, Messages[].class, senderId);
		List<Messages> messages = messagesArray != null ? Arrays.asList(messagesArray) : List.of();

		model.addAttribute("messages", messages);
		model.addAttribute("resident",resident);
		ModelAndView modelAndView = new ModelAndView("messagesBySender");
		return modelAndView;
	}

	// view Received messages
	@GetMapping("/receiver")
	public ModelAndView getMessagesByReceiver(@RequestParam("receiverId") Long receiverId, Model model,HttpSession session) {
		Residents resident=(Residents) session.getAttribute("residents");
		String url = "http://localhost:8090/messages/receiver/{receiverId}";
		RestTemplate restTemplate=new RestTemplate();
		Messages[] messagesArray = restTemplate.getForObject(url, Messages[].class,receiverId);
		List<Messages> messages = messagesArray != null ? Arrays.asList(messagesArray) : List.of();

		model.addAttribute("messages", messages);
		model.addAttribute("resident",resident);
		ModelAndView modelAndView = new ModelAndView("messagesByReceiver");
		return modelAndView;
	}
	
	// Admin view all messages in descending order of dates
	@GetMapping("/view")
	public ModelAndView getAllMessages(Model model) {
		String url="http://localhost:8090/messages/admin/view";
		RestTemplate restTemplate=new RestTemplate();
		List<Messages> messages=List.of(restTemplate.getForObject(url,Messages[].class));
		model.addAttribute("messages",messages);
		return new ModelAndView("adminMessages");
	}
	
}
