package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.models.auth.ValidEmailDto;
import com.mjc813.login_cookie.models.member.MemberEntity;
import com.mjc813.login_cookie.models.member.MemberJpaRepository;
import com.mjc813.login_cookie.models.member.MemberService;
import com.mjc813.login_cookie.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	private MemberJpaRepository memberJpaRepository;

	public Boolean checkValidEmail(ValidEmailDto validEmailDto) {
		MemberEntity find = this.memberJpaRepository.findBySignId(validEmailDto.getSignId()).orElseThrow();
		if ( validEmailDto.getValidText().equals(find.getValidText()) ) {
			find.setIsValidEmail(true);
			find.setRole(Role.USER.toString());
			this.memberJpaRepository.save(find);
			return true;
		} else {
			return false;
		}
	}
}
