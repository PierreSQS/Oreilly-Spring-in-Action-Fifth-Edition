package pierrot.tacos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.repositories.IngredientRepository;


/**
 * This Class converts the ingredients IDs e.g [FLTO,CARN,CHED,LETC,SLSA]
 * coming from the selected checkboxes on the Taco Form as String 
 * to Ingredients POJOs. Before converting the IDs, the convert-Method
 * checks whether the ID Ingredient exists in the DB.
 * @author PierreSQS
 *
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	private IngredientRepository ingreRepo;

	@Autowired
	public IngredientByIdConverter(IngredientRepository ingreRepo) {
		super();
		this.ingreRepo = ingreRepo;
	}

	@Override
	public Ingredient convert(String id) {
		return ingreRepo.findById(id).orElse(null);
	}

}
