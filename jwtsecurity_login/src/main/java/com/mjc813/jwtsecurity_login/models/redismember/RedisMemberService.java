package com.mjc813.jwtsecurity_login.models.redismember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisMemberService {
	@Autowired
	private RedisMemberRepository redisMemberRepository;

	public RedisMemberDto insert(RedisMemberDto requestDto) {
		RedisMemberEntity redisMemberEntity = (RedisMemberEntity)new RedisMemberEntity().clone(requestDto, true);
		RedisMemberEntity saved = this.redisMemberRepository.save(redisMemberEntity);
		RedisMemberDto resultDto = (RedisMemberDto)new RedisMemberDto().clone(saved, true);
		return resultDto;
	}

	public RedisMemberDto findBySignId(String signId) {
		RedisMemberEntity findDto = this.redisMemberRepository.findBySignId(signId).orElse(null);
		if ( findDto == null) {
			return null;
		}
		RedisMemberDto resultDto = (RedisMemberDto)new RedisMemberDto().clone(findDto, true);
		return resultDto;
	}

	public RedisMemberDto update(RedisMemberDto requestDto) {
		RedisMemberDto findDto = this.findBySignId(requestDto.getSignId());
		if ( findDto == null) {
			return null;
		}
		findDto.clone(requestDto, false);
		RedisMemberEntity redisMemberEntity = (RedisMemberEntity)new RedisMemberEntity().clone(findDto, true);
		RedisMemberEntity saved = this.redisMemberRepository.save(redisMemberEntity);
		RedisMemberDto resultDto = (RedisMemberDto)new RedisMemberDto().clone(saved, true);
		return resultDto;
	}

	public RedisMemberDto deleteBySignId(String signId) {
		RedisMemberDto findDto = this.findBySignId(signId);
		if ( findDto == null) {
			return null;
		}
		this.redisMemberRepository.deleteById(signId);
		return findDto;
	}
}
