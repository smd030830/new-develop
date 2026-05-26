package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.common.LoginException;
import com.mjc813.login_cookie.models.auth.SignInDto;
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

	public Boolean signMember(SignInDto signInDto) throws LoginException {
		MemberEntity find = this.memberJpaRepository.findBySignId(signInDto.getSignId()).orElseThrow();
		if ( !find.getIsValidEmail() ) {
			throw new LoginException("not valid email");
		}
		if ( find.getRole().equals(Role.GUEST.toString()) ) {
			throw new LoginException("doesn't need login");
		}
		if ( signInDto.getPassword().equals(find.getPassword()) ) {
			return true;
		}
		return false;
	}
}
