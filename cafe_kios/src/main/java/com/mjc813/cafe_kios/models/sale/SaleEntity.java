package com.mjc813.cafe_kios.models.sale;

import com.mjc813.cafe_kios.models.product.ProductAEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="sale")
public class SaleEntity implements ISale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name="product_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductAEntity productId;

	@Column(nullable = false)
	private Integer qty;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private LocalDateTime saleTime;
}
