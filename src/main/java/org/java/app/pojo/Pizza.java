package org.java.app.pojo;





import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.app.controller.dto.PizzaDTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, length = 30, nullable = false)
	@NotBlank(message = "Il nome della pizza non può essere vuoto")
	@Length(
			max = 30,
			message = "Il nome non può essere più lungo di 30 caratteri"
		)
	private String name;
	
	@Lob
	@Column(columnDefinition="text")
	@NotBlank(message = "Il campo della descrizione non può essere vuoto")
	@Length(
			max = 500,
			message = "La descrizione non può essere più lunga di 500 caratteri"
		)
	private String description;
	
	@Lob
	@Column(columnDefinition="text")
	@NotBlank(message = "Il campo dell'url non può essere vuoto")
	@Length(
			max = 250,
			message = "L'url non può essere più lungo di 250 caratteri"
		)
	private String photo;
	
	@Column(nullable = false)
	@Positive(message = "Il prezzo deve essere un valore positivo")
	private double price;

	@OneToMany(mappedBy = "pizza")
	@JsonManagedReference 
	private List<SpecialOffer> specialOffers;
	
	
	@ManyToMany 
	@JsonManagedReference 
	private List<Ingredient> ingredients;
	public Pizza() {
		
	}

	
	public Pizza(String name, String description, String photo, double price) {
		setName(name); 
		setDescription(description); 
		setPhoto(photo); 
		setPrice(price);
	}
	
	public Pizza(PizzaDTO pizzaDTO) {
		setName(pizzaDTO.getName()); 
		setDescription(pizzaDTO.getDescription()); 
		setPhoto(pizzaDTO.getPhoto()); 
		setPrice(pizzaDTO.getPrice());
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}
	
	public void setSpecialOffers(List <SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public boolean hasIngredient(Ingredient ingredient) {
		if(getIngredients() == null) return false; 
		for (Ingredient i : getIngredients())
			if (ingredient.getId() == i.getId())
				return true;
		return false;
	}
	
	public void addIngredient (Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	public void removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}


	public void fillFromPizzaDto(PizzaDTO pizzaDTO) {
		setName(pizzaDTO.getName()); 
		setDescription(pizzaDTO.getDescription()); 
		setPhoto(pizzaDTO.getPhoto()); 
		setPrice(pizzaDTO.getPrice());
		
	}
}
