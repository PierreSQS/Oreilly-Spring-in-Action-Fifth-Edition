package pierrot.tacos.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Order;
import pierrot.tacos.repositories.IngredientRepository;
import pierrot.tacos.repositories.OrderRepository;

@Slf4j
@WebMvcTest(controllers = {OrderController.class})
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	OrderRepository orderRepoMock;
	
	private Order order;
	
	// this Mock is not really used, but is
	// only needed to instantiate the Converter (IngredientByIdConverter) 
	@MockBean
	IngredientRepository ingreRepoMock;

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
	void testShowOrderForm() throws Exception {
		mockMvc.perform(get("/orders/current"))
		    .andDo(print())
		    .andExpect(view().name("orderForm"));
	}

}
