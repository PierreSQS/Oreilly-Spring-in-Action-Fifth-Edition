package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	
	private LocalDateTime createdAt;
	
	private String name;

	private List<Ingredient> ingredients;

}