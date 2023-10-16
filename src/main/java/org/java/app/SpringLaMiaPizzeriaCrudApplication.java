package org.java.app;

import org.java.app.pojo.Ingredient;
import org.java.app.pojo.Pizza;
import org.java.app.serv.IngredientService;
import org.java.app.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired 
	private IngredientService ingredientService;
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	

	 
	@Override
	public void run(String... args) throws Exception {
		Ingredient ingredient1 = new Ingredient("Tomato");
	     Ingredient ingredient2 = new Ingredient("Mozzarella");
	     Ingredient ingredient3 = new Ingredient("Ham");

	        
	     ingredientService.save(ingredient1);
	     ingredientService.save(ingredient2);
	     ingredientService.save(ingredient3);
		Pizza margherita = new Pizza("Margherita", 
				"La margherita è un grande classico senza tempo. Con pomodoro, mozzarella fiordilatte e una foglia di basilico",
				"https://it.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=800&v=1644590028&width=800",
				6.50
				);
		Pizza marinara = new Pizza("Marinara",
				"La pizza più semplice che ci sia, con salsa di pomodoro, aglio, olio e origano",
				"https://www.finedininglovers.it/sites/g/files/xknfdk1106/files/2021-03/Ricetta%20pizza%20marinara%205%20pomodori.jpg",
				5.50
				);
		Pizza diavola = new Pizza("Diavola", 
				"Come la margherita, ma con il salame", 
				"https://imag.bonviveur.com/pizza-diavola-decorada-con-albahaca-fresca.jpg",
				7.5);
		
		pizzaService.save(margherita);
		pizzaService.save(marinara);
		pizzaService.save(diavola);
		
	}

}
