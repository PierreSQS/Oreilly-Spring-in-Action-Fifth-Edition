package pierrot.tacos.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private Long id;
	private LocalDateTime placedAt;
	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTacoDesign(Taco tacoDesign) {
		tacos.add(tacoDesign);
	}

}
