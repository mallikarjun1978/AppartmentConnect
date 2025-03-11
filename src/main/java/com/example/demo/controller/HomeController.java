package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Map the / endpoint to render the home.html page
    @GetMapping("/index")
    public String home(Model model) {
        // Add attributes to the model if necessary, for example:
         model.addAttribute("message", "Welcome to SpringBootApp!");

        // Return the name of the home view (home.html)
        return "index";
    }

    // Map the /about endpoint to render the about.html page
    @GetMapping("/about")
    public String about(Model model) {
        // Setting dynamic content to be displayed in the template
        model.addAttribute("aboutText", "Apex Computer Education is a leading institution offering a wide range of computer education and training programs. We aim to provide quality education in the field of technology, ensuring that our students are prepared for the challenges of the modern world.");
        model.addAttribute("missionText", "Our mission is to deliver high-quality computer education that empowers students with the skills and knowledge to succeed in the ever-evolving technology landscape.");
        model.addAttribute("visionText", "Our vision is to be the most respected institution for computer education, known for producing highly skilled, innovative, and successful professionals in the field of technology.");
        
        return "about"; // Returning the about.html page
    }

    // Map the /contact endpoint to render the contact.html page
    @GetMapping("/contact")
    public String contact() {
        // Return the contact view (contact.html)
        return "contact";
    }

    // Map the /login endpoint to render the login.html page
    @GetMapping("/login")
    public String login() {
        // Return the login view (login.html)
        return "login";
    }
}
