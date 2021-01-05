package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	
	private LocalDateTime createdAt;
	
	@Size(min = 5, message="Name must be at least 5 characters long")
	private String name;

	@NotNull(message="You must choose at least 1 ingredient" )
	private List<Ingredient> ingredients;

}