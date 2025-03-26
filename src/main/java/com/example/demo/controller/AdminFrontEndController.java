package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.MaintenanceRequest;
import com.example.demo.entity.Residents;
import com.example.demo.service.MaintenanceRequestService;

@Controller
@RequestMapping("/front-end/admin")
public class AdminFrontEndController {
	
	 
	 @GetMapping("/adminhome")
	    public ModelAndView login(Model model) {
	    	ModelAndView view = new ModelAndView("adminhome");
			return view;
	    }
}
