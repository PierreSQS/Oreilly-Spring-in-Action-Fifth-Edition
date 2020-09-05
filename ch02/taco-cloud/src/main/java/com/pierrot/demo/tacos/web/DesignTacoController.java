package com.pierrot.demo.tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pierrot.demo.tacos.Ingredient;
import com.pierrot.demo.tacos.Ingredient.Type;
import com.pierrot.demo.tacos.Taco;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		
		Arrays.asList(types).forEach(type -> {
			model.addAttribute(type.toString().toLowerCase(), 
					           filterByType(ingredients, type));
		});
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				.filter(ingr -> ingr.getType().equals(type))
				.collect(Collectors.toList());
	}

}
