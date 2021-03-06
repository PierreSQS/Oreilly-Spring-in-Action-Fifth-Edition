package pierrot.tacos.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Order;
import pierrot.tacos.repositories.OrderRepository;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderRepository orderRepo;	
	
	public OrderController(OrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Order order) {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order  order, Errors error, SessionStatus sessionStatus) {
		log.info("Processing Order: ...");

		if (error.hasErrors()) {
			return "orderForm";
		}
		
		orderRepo.save(order);
		
		sessionStatus.setComplete();
		
		log.info("Submitting Order: {}", order);
		return "redirect:/";
	}

}
