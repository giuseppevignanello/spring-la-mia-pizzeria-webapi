package org.java.app.controller;

import java.util.List;

import org.java.app.pojo.Pizza;
import org.java.app.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
@RequestMapping("/api/v1.0")
public class PizzaRestController {

	@Autowired 
	private PizzaService pizzaService; 
	
	@GetMapping 
	public ResponseEntity<List<Pizza>> getAll() {
		
		List<Pizza> pizzas = pizzaService.findAll();
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
		
	}
}
