package com.mjc813.swimpool.models.teacher;

import com.mjc813.swimpool.models.swimpool.SwimpoolEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
	@EntityGraph(attributePaths = {"swimpool"})
	Slice<TeacherEntity> findBySwimpool(SwimpoolEntity swimpool, Pageable pageable);

	@EntityGraph(attributePaths = {"swimpool"})
	Slice<TeacherEntity> findByNameContaining(String name, Pageable pageable);
}
