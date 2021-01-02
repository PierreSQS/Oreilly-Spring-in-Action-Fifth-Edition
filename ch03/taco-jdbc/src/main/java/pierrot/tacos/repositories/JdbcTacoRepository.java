package pierrot.tacos.repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Taco;

public class JdbcTacoRepository implements TacoRepository {
	
	private JdbcTemplate jdbcTemp;
		
	public JdbcTacoRepository(JdbcTemplate jdbcTemp) {
		super();
		this.jdbcTemp = jdbcTemp;
	}



	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		taco.getIngredients().forEach(ingred -> {saveIngredientToTaco(ingred, tacoId);});
		return taco;
	}



	private long saveTacoInfo(Taco taco) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
		// TODO Auto-generated method stub		
	}

}
