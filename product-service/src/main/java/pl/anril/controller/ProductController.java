package pl.anril.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.anril.model.Inventory;
import pl.anril.model.Price;
import pl.anril.model.Product;
import pl.anril.model.ProductInfo;

@RestController
public class ProductController {

	List<ProductInfo> productList = List.<ProductInfo>of(new ProductInfo(101L, "iPhone", "expensive as fuck"),
			new ProductInfo(102L, "book", "reading is fun"),
			new ProductInfo(103L, "washing machine", "washing mc is necessary"));

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/product/details/{productid}")
	public Product getProductInfo(@PathVariable Long productid) {
		ProductInfo productInfo = productList.stream().filter(el -> Objects.equals(productid, el.getId())).findFirst()
				.orElse(null);
		Price price = restTemplate.getForObject("http://localhost:9002/product/price/" + productid, Price.class);
		Inventory inventory = restTemplate.getForObject("http://localhost:9003/product/inventory/" + productid,
				Inventory.class);
		return Objects.nonNull(productInfo)
				? new Product(productid, productInfo.getName(), productInfo.getDesc(),
						Objects.nonNull(price) ? price.getOriginalPrice() : BigDecimal.ZERO,
						Objects.nonNull(inventory) ? inventory.getInStock() : Boolean.FALSE)
				: null;
	}
}
