package com.mjc813.login_cookie.models.member;

import com.mjc813.login_cookie.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
	@Autowired
	private MemberJpaRepository memberJpaRepository;

	public MemberDto insert(MemberDto memberDto) {
		MemberEntity memberEntity = (MemberEntity)new MemberEntity().clone(memberDto, true);
		memberEntity.setId(null);
		memberEntity.setCreateDt(LocalDateTime.now());
		memberEntity.setIsValidEmail(false);
		memberEntity.setRole(Role.USER.toString());
		MemberEntity saved = this.memberJpaRepository.save(memberEntity);
		MemberDto result = (MemberDto)new MemberDto().clone(saved, true);
		return result;
	}

	public List<MemberDto> findAll() {
		List<MemberEntity> all = this.memberJpaRepository.findAll();
		List<MemberDto> result = this.transfer(all);
		return result;
	}

	private List<MemberDto> transfer(List<MemberEntity> all) {
		return all.stream()
			.map( x -> (MemberDto)new MemberDto().clone(x, true))
			.toList();
	}
}
