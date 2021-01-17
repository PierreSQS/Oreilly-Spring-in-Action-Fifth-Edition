package pierrot.tacos.repositories;

import org.springframework.data.repository.CrudRepository;

import pierrot.tacos.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
