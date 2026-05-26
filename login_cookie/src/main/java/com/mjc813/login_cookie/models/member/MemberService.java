package com.mjc813.login_cookie.models.member;

import com.mjc813.login_cookie.common.Util;
import com.mjc813.login_cookie.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
	@Autowired
	private MemberJpaRepository memberJpaRepository;

	public MemberDto insert(MemberDto memberDto, boolean bAdminMode) {
		MemberEntity memberEntity = (MemberEntity)new MemberEntity().clone(memberDto, true);
		memberEntity.setId(null);
		memberEntity.setCreateDt(LocalDateTime.now());
		if ( bAdminMode ) {
			memberEntity.setIsValidEmail(true);
			memberEntity.setRole(Role.USER.toString());
		} else {
			memberEntity.setIsValidEmail(false);
			memberEntity.setRole(Role.GUEST.toString());
			memberEntity.setValidText(Util.getRandomAllString(12));
		}
		MemberEntity saved = this.memberJpaRepository.save(memberEntity);
		MemberDto result = (MemberDto)new MemberDto().clone(saved, true);
		return result;
	}

	public MemberDto findById(String id) {
		MemberEntity findById = this.memberJpaRepository.findById(Long.parseLong(id)).orElseThrow();
		MemberDto result = (MemberDto)new MemberDto().clone(findById, true);
		return result;
	}

	public MemberDto update(MemberDto updateDto) {
		MemberEntity find = this.memberJpaRepository.findById(updateDto.getId()).orElseThrow();
		MemberEntity memberEntity = (MemberEntity)new MemberEntity().clone(find, true);
		memberEntity.clone(updateDto, false);
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

	public MemberDto findBySignId(String signId) {
		Optional<MemberEntity> bySignId = this.memberJpaRepository.findBySignId(signId);
		if ( bySignId.isPresent() ) {
			MemberEntity member = bySignId.get();
			MemberDto result = (MemberDto)new MemberDto().clone(member, true);
			return result;
		} else {
			return null;
		}
	}
}
