package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AppartmentImagesService;

@RestController
@RequestMapping("api/v1")
public class AppartmentImagesController {
	@Autowired
	AppartmentImagesService appartmentImagesService;
	@PostMapping(value = "/addImage")
	public ResponseEntity<app> addProduct(@RequestBody Product product){
		System.out.println("product:" + product);
		productService.addProduct(product);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
}
