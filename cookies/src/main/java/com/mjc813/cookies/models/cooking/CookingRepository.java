package com.mjc813.cookies.models.cooking;

import com.mjc813.cookies.models.cookie.CookieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingRepository extends JpaRepository<CookingEntity, Long> {
	@EntityGraph(attributePaths = {"cookie"})
	Slice<CookingEntity> findAllByDescriptionContaining(String description, Pageable pageable);

	@EntityGraph(attributePaths = {"cookie"})
	Slice<CookingEntity> findAllByCookieEquals(CookieEntity cookie, Pageable pageable);
}
