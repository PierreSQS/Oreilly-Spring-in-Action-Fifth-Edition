package pierrot.tacos.controllers;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Ingredient.Type;
import pierrot.tacos.domain.Order;
import pierrot.tacos.domain.Taco;
import pierrot.tacos.repositories.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	
	public DesignTacoController(IngredientRepository ingredientRepo) {
		super();
		this.ingredientRepo = ingredientRepo;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		log.info("ModeAttribute Order");
		return new Order();
	}
	
	// this replace the formal ModelAttribute
	// in the controller
	@ModelAttribute(name = "taco")
	public Taco taco() {
		log.info("ModeAttribute Taco");
		return new Taco();
	}
	  
	@GetMapping
	public String showDesignForm(Model model) {
		// fetch the ingredients from the Database
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
		
		// Group the ingredients by type in a list, and put the results in a Map
		Map<Type, List<Ingredient>> ingredientsByType =
				ingredients.stream().collect(groupingBy(Ingredient::getType));
		
		// iterate through the Map and set the model attributes for the checkbox
		ingredientsByType.forEach((type, ingredList)-> {model.addAttribute(type.toString().toLowerCase(),ingredList);});
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors error) {

		if (error.hasErrors()) {
			log.info("Error Handling in processing Taco {}", taco);
			return "design";
		}

		// Handling the submitted Taco Design...
		log.info("Processing Taco {}...", taco);
		return "redirect:/orders/current";
	}

}
