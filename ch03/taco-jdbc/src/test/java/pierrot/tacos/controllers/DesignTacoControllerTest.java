package pierrot.tacos.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pierrot.tacos.repositories.IngredientRepository;
import pierrot.tacos.repositories.TacoRepository;

@WebMvcTest(DesignTacoController.class)
class DesignTacoControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IngredientRepository ingredRepo;

	@MockBean
	TacoRepository tacoRepo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
