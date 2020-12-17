package pl.anril.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.anril.model.Inventory;

@RestController
public class InventoryController {
	List<Inventory> inventoryList = List.<Inventory>of(new Inventory(301L, 101L, Boolean.TRUE),
			new Inventory(302L, 102L, Boolean.FALSE), new Inventory(303L, 103L, Boolean.TRUE));

	@GetMapping("/product/inventory/{productid}")
	public Inventory getPriceInfo(@PathVariable Long productid) {
		return inventoryList.stream().filter(el -> Objects.equals(productid, el.getProductId())).findFirst()
				.orElse(null);

	}
}
