package pierrot.tacos.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pierrot.tacos.repositories.IngredientRepository;
import pierrot.tacos.repositories.OrderRepository;
import pierrot.tacos.repositories.TacoRepository;

@WebMvcTest()
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	IngredientRepository ingreRepoMock;
	
	@MockBean
	TacoRepository tacoRepoMock;
	
	@MockBean
	OrderRepository orderRepoMock;

	@Test
	void testHome() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(content().string(containsString("Taco Cloud Security")));
	}

}
