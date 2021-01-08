package pierrot.tacos.repositories;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import pierrot.tacos.domain.Order;

@Slf4j
@Repository
public class JdbcOrderRepository implements OrderRepository {

	private JdbcTemplate jdbcTemp;

	private SimpleJdbcInsert insertOrder;

	private SimpleJdbcInsert insertTaco;

	private ObjectMapper objMapper;

	public JdbcOrderRepository(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
		insertOrder = new SimpleJdbcInsert(jdbcTemp).withTableName("Taco_Order").usingGeneratedKeyColumns("id");

		insertTaco = new SimpleJdbcInsert(jdbcTemp).withTableName("Taco_Order_Tacos");

		objMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
		order.setPlacedAt(LocalDateTime.now());
		long orderId = saveOrder(order);
		order.getTacos().forEach(taco -> saveTaco(orderId, taco.getId()));
		return order;
	}

	private long saveOrder(Order order) {
		Map<String, Object> values = objMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		Number orderId = insertOrder.executeAndReturnKey(values);
		return orderId.longValue();
	}

	private void saveTaco(long orderId, Long tacoId) {
		log.info("Entering save in Taco_Order_Tacos...");
		jdbcTemp.update("insert into Taco_Order_Tacos values(?,?)", orderId, tacoId);

	}

}
