package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime createdAt;
	
	@Size(min = 5, message="Name must be at least 5 characters long")
	private String name;

	@NotNull(message="You must choose at least 1 ingredient" )
	@ManyToMany(targetEntity = Ingredient.class)
	private List<Ingredient> ingredients;

}