package pl.anril.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.anril.model.CurrencyEx;

@RestController
public class CurrencyController {
	List<CurrencyEx> priceList = List.of(new CurrencyEx(901L, "USD", "JPY", BigDecimal.valueOf(103.08)),
			new CurrencyEx(902L, "USD", "PLN", BigDecimal.valueOf(3, 62)),
			new CurrencyEx(903L, "USD", "GBP", BigDecimal.valueOf(0.74)));

	@GetMapping("/currexg/from/{from}/to/{to}")
	public CurrencyEx getPriceInfo(@PathVariable("from") String from, @PathVariable("to") String to) {
		return priceList.stream().filter(el -> Objects.equals(from, el.getFrom()) && Objects.equals(to, el.getTo()))
				.findFirst().orElse(null);

	}
}
