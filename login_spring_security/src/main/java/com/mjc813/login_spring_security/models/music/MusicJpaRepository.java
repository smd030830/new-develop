package com.mjc813.login_spring_security.models.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicJpaRepository extends JpaRepository<MusicEntity, Long> {
	Optional<MusicEntity> findByIdAndDeleteIdIsNull(long id);   // 이 방법은 관리자도 조회 못한다.
	List<MusicEntity> findAllByDeleteIdIsNull();
}
