package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private Long id;
	private LocalDateTime placedAt;
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTacoDesign(Taco tacoDesign) {
		tacos.add(tacoDesign);
	}

}
