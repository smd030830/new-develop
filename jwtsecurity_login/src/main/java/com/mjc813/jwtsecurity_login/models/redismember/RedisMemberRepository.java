package com.mjc813.jwtsecurity_login.models.redismember;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisMemberRepository extends ListCrudRepository<RedisMemberEntity, String> {
	Optional<RedisMemberEntity> findBySignId(String signId);
}
