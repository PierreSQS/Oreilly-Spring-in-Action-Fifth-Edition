package pierrot.tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Ingredient.Type;
import pierrot.tacos.repositories.IngredientRepository;

@Slf4j
@SpringBootApplication
public class TacoJpaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoJpaApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	
//	@Bean
//	public CommandLineRunner ingredientsLoaderToDB(IngredientRepository ingredientRepo) {
//		return args -> {
//			log.info("Loading Ingredients to the DB...");
//			ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//			ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//			ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//			ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//			ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//			ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//			ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//			ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//			ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//			ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//		};
//	}

}
