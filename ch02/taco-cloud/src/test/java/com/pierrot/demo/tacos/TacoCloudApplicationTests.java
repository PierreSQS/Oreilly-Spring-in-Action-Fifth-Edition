package com.pierrot.demo.tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.pierrot.demo.tacos.web.DesignTacoController;
import com.pierrot.demo.tacos.web.OrderController;

@WebMvcTest(controllers = {HomeController.class, DesignTacoController.class, OrderController.class})
class TacoCloudApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"))
		.andExpect(content().string(containsString("Welcome to...")));
	}

	@Test
	void testShowDesignForm() throws Exception {
		mockMvc.perform(get("/design"))
		.andExpect(status().isOk())
		.andExpect(view().name("design"))
		.andExpect(content().string(containsString("Design your taco")));
	}

	@Test
	void testShowDesignForm_DesignTacoController() throws Exception {
		mockMvc.perform(post("/design"))
//				.param("name", "Top Gun")) // not for this form
		.andExpect(status().is3xxRedirection())
//		.andReturn() // to check what assertion does
		.andExpect(view().name("redirect:/orders/current"))
		.andExpect(redirectedUrl("/orders/current"));
//		.andDo(print()); // to print the Results by all means
	}

	@Test
	void testOrderForm_OrderController() throws Exception {
		mockMvc.perform(get("/orders/current")
				.param("name", "Top Gun"))
		.andExpect(model().attributeExists("order"))
		.andExpect(status().isOk())
		.andExpect(view().name("orderForm"))
		.andExpect(content().string(containsString("Design another taco")));
//		.andDo(print()); // to print the Results by all means
	}

	@Test
	void testProcessOrder_OrderController() throws Exception {
		mockMvc.perform(post("/orders"))
		.andExpect(status().isOk())
		.andExpect(view().name("orderForm"))
		.andExpect(content().string(containsString("Design another taco")));
	}
}
