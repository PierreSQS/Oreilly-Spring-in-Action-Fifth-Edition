package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	
	private LocalDateTime createdAt;
	
	@NotEmpty
	@Size(min = 5)
	private String name;

	@Size(min = 1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

}