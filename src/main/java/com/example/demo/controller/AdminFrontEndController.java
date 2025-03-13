package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Residents;

@Controller
@RequestMapping("/front-end/admin")
public class AdminFrontEndController {

	 @GetMapping("/adminhome")
	    public ModelAndView login(Model model) {
	    	ModelAndView view = new ModelAndView("adminhome");
			return view;
	    }
}
