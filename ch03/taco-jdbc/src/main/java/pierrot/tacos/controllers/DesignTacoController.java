package pierrot.tacos.controllers;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Ingredient.Type;
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

	@GetMapping
	public String showDesignForm(Model model) {
		// fetch the ingredients from the Database
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
		
		// Group the ingredients by type in a list, and put the results in a Map
		Map<Type, List<Ingredient>> ingredientsByType =
				ingredients.stream().collect(groupingBy(Ingredient::getType));
		
		// iterate through the Map and set the model attributes for the checkbox
		ingredientsByType.forEach((type, ingredList)-> {model.addAttribute(type.toString().toLowerCase(),ingredList);});
		
		// set the model attribute for the taco design
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processDesign(Taco design) {
		log.info("Processing Taco {}...", design);
		// Handling the submitted Taco Design...
		return "redirect:/orders/current";
	}

}
