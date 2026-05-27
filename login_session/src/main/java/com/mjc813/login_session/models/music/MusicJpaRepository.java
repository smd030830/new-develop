package com.mjc813.login_session.models.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicJpaRepository extends JpaRepository<MusicEntity, Long> {
}
