package pierrot.tacos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.repositories.IngredientRepository;


/**
 * This Class converts the ingredients ids e.g [FLTO,CARN,CHED,LETC,SLSA]
 * coming from the Taco Form * as String to Ingredients coming from
 * the Database
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
		return ingreRepo.findOne(id);
	}

}
