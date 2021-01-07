package pierrot.tacos.repositories;

import org.springframework.stereotype.Repository;

import pierrot.tacos.domain.Order;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
