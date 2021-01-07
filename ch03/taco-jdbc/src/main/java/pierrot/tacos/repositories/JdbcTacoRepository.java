package pierrot.tacos.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pierrot.tacos.domain.Ingredient;
import pierrot.tacos.domain.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbcTemp;
	
	private SimpleJdbcInsert insertTaco;
	
	public JdbcTacoRepository(JdbcTemplate jdbcTemp) {
		super();
		this.jdbcTemp = jdbcTemp;
		this.insertTaco = new SimpleJdbcInsert(jdbcTemp)
				.withTableName("Taco")
				.usingGeneratedKeyColumns("id");

	}

	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		taco.getIngredients().forEach(ingred -> {
			saveIngredientToTaco(ingred, tacoId);
		});
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(LocalDateTime.now());
		Map<String, Object> params = new HashMap<>();
		params.put("name", taco.getName());
		params.put("createdAt", taco.getCreatedAt());
		Number newId = insertTaco.executeAndReturnKey(params);
		return newId.longValue();
	}

	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
		jdbcTemp.update("insert into Taco_Ingredients values("+tacoId+ ","+ ingredient.getId()+")");
	}

}
