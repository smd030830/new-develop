package com.mjc813.cafe_kiosk.models.sale;

import com.mjc813.cafe_kiosk.models.category.CategoryDto;
import com.mjc813.cafe_kiosk.models.product.ProductDto;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDto {
    private Integer id;
    private ProductDto product;
    private Integer price;
    private Integer qty;
    private LocalDateTime saleTime;
}
