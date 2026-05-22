package com.mjc813.cafe_kiosk.models.product;

import com.mjc813.cafe_kiosk.models.category.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	@EntityGraph(value = "ProductEntity.fetchCategory")
	Slice<ProductEntity> findByNameContains(String name, Pageable pageable);

	List<ProductEntity> findByPriceGreaterThanOrderByIdDesc(Integer price);

	Page<ProductEntity> findByCategoryEquals(CategoryEntity category, Pageable pageable);
}
