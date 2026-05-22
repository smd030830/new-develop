package com.mjc813.cookies.models.cookie;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<CookieEntity, Long> {
	Slice<CookieEntity> findByNameContains(String name, Pageable pageable);
}
