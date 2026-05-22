package com.mjc813.cookies.models.category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	Slice<CategoryEntity> findByNameContains(String name, Pageable pageable);
}
