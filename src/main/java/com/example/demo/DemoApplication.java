package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
//          String password = "12345";
//        
//        // Create an instance of BCryptPasswordEncoder
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        
//        // Encode the password
//        String encodedPassword = encoder.encode(password);
//        
//        // Print the encoded password, this password store in db table:user
//        System.out.println("Encoded password: " + encodedPassword);
	}

}
