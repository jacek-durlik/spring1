package pl.anril.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.anril.model.CurrencyEx;
import pl.anril.model.Price;

@RestController
public class PricingController {
	List<Price> priceList = List.<Price>of(
			new Price(201L, 101L, BigDecimal.valueOf(1999.99), BigDecimal.valueOf(999.99)),
			new Price(202L, 102L, BigDecimal.valueOf(199), BigDecimal.valueOf(19)),
			new Price(203L, 103L, BigDecimal.valueOf(1222.22), BigDecimal.valueOf(600)));

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/product/price/{productid}")
	public Price getPriceInfo(@PathVariable Long productid) {
		CurrencyEx exgRate = restTemplate.getForObject("http://localhost:9004/currexg/from/USD/to/JPY",
				CurrencyEx.class);
		Price price = priceList.stream().filter(el -> Objects.equals(productid, el.getProductId())).findFirst()
				.orElse(null);
		if (Objects.nonNull(price) && Objects.nonNull(exgRate)) {
			return new Price(price.getId(), price.getProductId(),
					exgRate.getExgVal().multiply(price.getOriginalPrice()),
					exgRate.getExgVal().multiply(price.getDiscountedPrice()));
		}
		return null;

	}
}
