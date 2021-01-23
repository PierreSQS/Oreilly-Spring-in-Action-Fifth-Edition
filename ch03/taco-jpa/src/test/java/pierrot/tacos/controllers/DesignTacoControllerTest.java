package pierrot.tacos.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
	private IngredientRepository ingredRepoMock;

	@MockBean
	private TacoRepository tacoRepoMock;

	private List<Ingredient> ingredients;

	private Taco design;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		when(ingredRepoMock.findAll()).thenReturn(ingredients);

		when(ingredRepoMock.findById("FLTO")).thenReturn(Optional.of(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP)));

		when(ingredRepoMock.findById("JACK")).thenReturn(Optional.of(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE)));

		when(ingredRepoMock.findById("LETC")).thenReturn(Optional.of(new Ingredient("LETC", "Lettuce", Type.VEGGIES)));

		design = new Taco();
		design.setName("Test Taco");

		;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test 
	// The accuracy of this test must be checked
	public void testShowDesignFormWithoutModelAttributes() throws Exception {
		mockMvc.perform(get("/design")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("<title>Taco Cloud JPA</title>")))
				.andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
				.andExpect(model().attribute("protein", ingredients.subList(2, 4)))
				.andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
				.andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
				.andExpect(model().attribute("sauce", ingredients.subList(8, 10)))
				.andExpect(view().name("design"));
	}

	@Test
	public void testProcessTacoDesignWithoutIngredients() throws Exception {
		mockMvc.perform(post("/design")).andDo(print())
				// Show design view since no Ingredients checked
				.andExpect(view().name("design"))
				// We display the title of the design view
				.andExpect(content().string(containsString("<title>Taco Cloud JPA</title>")))
				// Display the error message since no Ingredient
				.andExpect(content().string(containsString("You must choose at least 1 ingredient")))
				// more accurate for this Test
				.andExpect(model().hasErrors());
	}

	@Test
	public void testProcessTacoDesignWitIngredients() throws Exception {
		when(tacoRepoMock.save(design)).thenReturn(design);

		mockMvc.perform(post("/design").content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(header().stringValues("Location", "/orders/current"));
	}

	@Test
	public void testProcessTacoDesignWitIngredientsParams() throws Exception {
		when(tacoRepoMock.save(design)).thenReturn(design);

		mockMvc.perform(post("/design")
//					.content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
					.param("name", "Test Taco")
					.param("ingredients", "FLTO", "GRBF", "CHED")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(header().stringValues("Location", "/orders/current"));
	}

}
