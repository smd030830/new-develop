package com.mjc813.sbsecurity_login.models.member;

import com.mjc813.sbsecurity_login.common.Mjc813Exception;
import com.mjc813.sbsecurity_login.common.Util;
import com.mjc813.sbsecurity_login.models.music.MusicDto;
import com.mjc813.sbsecurity_login.models.music.MusicEntity;
import com.mjc813.sbsecurity_login.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberJpaRepository memberJpaRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		memberEntity.setPassword(this.passwordEncoder.encode(memberEntity.getPassword()));
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

//	public List<MemberDto> findAll() {
//		List<MemberEntity> all = this.memberJpaRepository.findAll();
//		List<MemberDto> result = this.transfer(all);
//		return result;
//	}

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.findBySignId(username);
	}

	public boolean isCreateId(Long memId, String signId) throws Mjc813Exception {
		MemberDto findid = this.findById(signId);    // id 로 자료를 찾는다.
		if (findid != null && findid.getCreateId().equals(signId)) {
			return true;
		}
		return false;
	}
	public List<MemberDto> findAll() throws Mjc813Exception {
		// findAll 은 모든 자료를 조회해서 리턴하므로 자료의 갯수가 전부 20~30개 정도를 넘으면 별로 안좋은 기능이다.
		// 그 보다 많은 데이터를 조회하려면 Paging 이나 Slicing 으로 조회하세요
		List<MemberEntity> memberEntities = this.memberJpaRepository.findAllByDeleteIdIsNull();
		List<MemberDto> result = this.transfer(memberEntities);
		return result;
	}
}
