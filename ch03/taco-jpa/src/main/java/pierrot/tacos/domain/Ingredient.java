package pierrot.tacos.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Ingredient {

	@Id
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
