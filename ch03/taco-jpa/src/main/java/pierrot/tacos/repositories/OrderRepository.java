package pierrot.tacos.repositories;

import org.springframework.data.repository.CrudRepository;

import pierrot.tacos.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
