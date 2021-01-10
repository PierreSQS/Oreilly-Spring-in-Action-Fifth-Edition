package pierrot.tacos.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Ingredient.Type;
import pierrot.tacos.domain.Taco;
import pierrot.tacos.repositories.IngredientRepository;
import pierrot.tacos.repositories.TacoRepository;

@WebMvcTest(DesignTacoController.class)
class DesignTacoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private static IngredientRepository ingredRepoMock;

	@MockBean
	private static TacoRepository tacoRepoMock;

	private static List<Ingredient> ingredients;

	private static Taco design;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ingredients = Arrays.asList(
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
		
		 when(ingredRepoMock.findAll())
	        .thenReturn(ingredients);
		 
		 when(ingredRepoMock.findById("FLTO"))
		 	.thenReturn(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		 
		 when(ingredRepoMock.findById("JACK"))
		 	.thenReturn(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		 
		 when(ingredRepoMock.findById("LETC"))
		 	.thenReturn(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		 
		 
		 design = new Taco();
		 design.setName("Test Taco");
		 
		 when(tacoRepoMock.save(design));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testShowDesignFormWithoutModelAttributes() throws Exception {
		mockMvc.perform(get("/design"))
//                    .andDo(print())
		              .andExpect(status().isOk())
		              .andExpect(content().string(containsString("<title>Taco Cloud JDBC</title>")))
		              .andExpect(view().name("design"));
	}
	
	@Test
	void testProcessTacoDesignWithoutIngredients() throws Exception {
		mockMvc.perform(post("/design"))		
		              .andDo(print())
		              // Show design view since no Ingredients checked
		              .andExpect(view().name("design"))
		              // We display the title of the design view
		              .andExpect(content().string(containsString("<title>Taco Cloud JDBC</title>")))
		              // Display the error message since no Ingredient
		              .andExpect(content().string(containsString("You must choose at least 1 ingredient"))); 
	}

}
