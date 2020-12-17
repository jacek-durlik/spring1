package pl.anril.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
	private Long id;
	private Long productId;
	private BigDecimal originalPrice;
	private BigDecimal discountedPrice;
}
