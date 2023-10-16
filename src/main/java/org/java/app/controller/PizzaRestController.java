package org.java.app.controller;

import java.util.List;

import java.util.Optional;
import org.java.app.controller.dto.PizzaDTO;
import org.java.app.pojo.Pizza;
import org.java.app.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping 
	public ResponseEntity<Integer> save(
		@RequestBody PizzaDTO pizzaDTO
	) {
		Pizza pizza = new Pizza(pizzaDTO); 
		
		pizza = pizzaService.save(pizza);
		return new ResponseEntity<>(pizza.getId(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(
		@PathVariable int id
	) {
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(
			@PathVariable int id,
			@RequestBody PizzaDTO pizzaDTO
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Pizza pizza= optPizza.get();
		
		pizza.fillFromPizzaDto(pizzaDTO);
	
		try {
			
			pizza = pizzaService.save(pizza);
			
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Pizza pizza = optPizza.get();
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}		
	
	
}
