package com.mjc813.session_login.models.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicJpaRepository extends JpaRepository<MusicEntity, Long> {
}
