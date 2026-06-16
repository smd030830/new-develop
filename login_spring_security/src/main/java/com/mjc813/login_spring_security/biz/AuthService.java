package com.mjc813.login_spring_security.biz;

import com.mjc813.login_spring_security.common.LoginException;
import com.mjc813.login_spring_security.models.auth.SignInDto;
import com.mjc813.login_spring_security.models.auth.ValidEmailDto;
import com.mjc813.login_spring_security.models.member.MemberEntity;
import com.mjc813.login_spring_security.models.member.MemberJpaRepository;
import com.mjc813.login_spring_security.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
//	@Autowired
//	private PasswordEncoder pwdEncoder;
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

//	public Boolean signMember(SignInDto signInDto) throws LoginException {
//		MemberEntity find = this.memberJpaRepository.findBySignId(signInDto.getSignId()).orElseThrow();
//		if ( !find.getIsValidEmail() ) {
//			throw new LoginException("not valid email");
//		}
//		if ( find.getRole().equals(Role.GUEST.toString()) ) {
//			throw new LoginException("doesn't need login");
//		}
////		if ( signInDto.getPassword().equals(find.getPassword()) ) {
//		if ( pwdEncoder.matches(signInDto.getPassword(), find.getPassword())) {
//			return true;
//		}
//		return false;
//	}
}
