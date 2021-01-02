package pierrot.tacos.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pierrot.tacos.domain.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbcTemp;
	
	private RowMapper<Ingredient> rowMapper = (rs, rowNum) -> {
		return new Ingredient(rs.getString("id"), 
							  rs.getString("name"), 
							  Ingredient.Type.valueOf(rs.getString("type")));
	};

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbcTemp) {
		super();
		this.jdbcTemp = jdbcTemp;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemp.query("select id, name, type from Ingredient", this.rowMapper);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbcTemp.queryForObject("select * from Ingredient Ing where Ing.id = ?", this.rowMapper, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemp.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}

}
