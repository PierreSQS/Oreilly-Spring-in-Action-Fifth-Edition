package pierrot.tacos.repositories;

import pierrot.tacos.domain.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();

	Ingredient findById(String id);

	Ingredient save(Ingredient ingredient);
}