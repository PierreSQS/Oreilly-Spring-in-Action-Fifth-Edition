package pierrot.tacos.repositories;

import pierrot.tacos.domain.Order;

public interface OrderRepository {
	Order save(Order order);  
}
