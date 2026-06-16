package com.mjc813.login_session.models.music;

import com.mjc813.login_session.common.AuthorizedException;
import com.mjc813.login_session.common.LoginException;
import com.mjc813.login_session.common.Mjc813Exception;
import com.mjc813.login_session.common.ResponseCode;
import com.mjc813.login_session.models.member.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
	@Autowired
	private MusicJpaRepository musicJpaRepository;

	public MusicDto insert(IMember signedMember, MusicDto insertDto) throws LoginException {
		if ( signedMember == null ) {
			throw new LoginException("is not valid member");
		}
		MusicEntity musicEntity = (MusicEntity)new MusicEntity().copyMembers(insertDto, true);
		musicEntity.setId(null);
		musicEntity.setCreateId(signedMember.getSignId());
		musicEntity.setCreateDt(LocalDateTime.now());
		MusicEntity saved = this.musicJpaRepository.save(musicEntity);
		MusicDto musicDto = (MusicDto)new MusicDto().copyMembers(saved, true);
		return musicDto;
	}

	public MusicDto update(Model model, MusicDto updateDto) throws Mjc813Exception {
//		MusicEntity findEntity = this.musicJpaRepository.findByIdAndDeleteIdIsNull(updateDto.getId()).orElseThrow();
//		MusicDto findDto = (MusicDto)new MusicDto().copyMembers(findEntity, true);
		MusicDto findDto = this.findById(updateDto.getId());    // id 로 자료를 찾는다.
		IMember signedMember = this.isCreateIdOwnerOrAdmin(model, findDto); // 로그인 클라이언트의 인가 맞는 지 체크
		if ( signedMember == null) {
			throw new Mjc813Exception(ResponseCode.AUTHORIZATION_ERROR, "authorize is not allow for update");
		}
		findDto.setUpdateId(signedMember.getSignId());
		findDto.setUpdateDt(LocalDateTime.now());
		findDto.copyMembers(updateDto, false);
		MusicEntity updateEntity = (MusicEntity)new MusicEntity().copyMembers(findDto, true);
		MusicEntity savedEntity = this.musicJpaRepository.save(updateEntity);
		MusicDto returnDto = (MusicDto)new MusicDto().copyMembers(savedEntity, true);
		return returnDto;
	}

	public MusicDto deleteById(Model model, Long id) throws Mjc813Exception {
		MusicDto findDto = this.findById(id);    // id 로 자료를 찾는다.
		IMember signedMember = this.isCreateIdOwnerOrAdmin(model, findDto); // 로그인 클라이언트의 인가 맞는 지 체크
		if ( signedMember == null) {
			throw new Mjc813Exception(ResponseCode.AUTHORIZATION_ERROR, "authorize is not allow for delete");
		}
		findDto.setDeleteId(signedMember.getSignId());
		findDto.setDeleteDt(LocalDateTime.now());
		MusicEntity deleteEntity = (MusicEntity)new MusicEntity().copyMembers(findDto, true);
		MusicEntity savedEntity = this.musicJpaRepository.save(deleteEntity);
		MusicDto returnDto = (MusicDto)new MusicDto().copyMembers(savedEntity, true);
		return returnDto;
	}

	// createId 가 자기자신이고, role 이 ADMIN 인지 체크해서 아니면 null 을 리턴
	private IMember isCreateIdOwnerOrAdmin(Model model, MusicDto findDto) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember != null ) {
			// 로그인을 한 클라이언트의 요청인 경우
			if ( signedMember.getRole().equals("USER") ) {
				// 로그인을 한 클라이언트의 role 이 "USER" 인 경우
				if ( signedMember.getSignId().equals(findDto.getCreateId()) ) {
					// 로그인을 한 클라이언트의 signId 가 찾은 MusicDto 의 createId 랑 같은 경우
					return signedMember;
				}
			} else if ( signedMember.getRole().equals("ADMIN") ) {
				return signedMember;
			}
		}
		return null;
	}

	public MusicDto findById(Long id) throws Mjc813Exception {
		Optional<MusicEntity> musicEntity = this.musicJpaRepository.findByIdAndDeleteIdIsNull(id);
		if ( musicEntity.isEmpty() ) {
			throw new Mjc813Exception(ResponseCode.DATA_NOT_FOUND_ERROR, "data is not exist");
		}
		MusicDto find = (MusicDto)new MusicDto().copyMembers(musicEntity.get(), true);
		return find;
//		MusicEntity musicEntity = this.musicJpaRepository.findById(id).orElseThrow();
//		MusicDto find = (MusicDto)new MusicDto().copyMembers(musicEntity, true);
//		if ( find == null || find.getDeleteId() != null ) {
//			// deleteId 가 null 이 아니면 삭제된 데이터를 뜻한다.
//			throw new Mjc813Exception(ResponseCode.DATA_NOT_FOUND_ERROR, "data is not exist");
//		}
//		return find;
	}

	public List<MusicDto> findAll(Model model) throws Mjc813Exception {
		// findAll 은 모든 자료를 조회해서 리턴하므로 자료의 갯수가 전부 20~30개 정도를 넘으면 별로 안좋은 기능이다.
		// 그 보다 많은 데이터를 조회하려면 Paging 이나 Slicing 으로 조회하세요
		IMember signedMember = this.isAdmin(model);
		if ( signedMember == null ) {
			throw new Mjc813Exception(ResponseCode.AUTHORIZATION_ERROR, "authorize is now allow for findall");
		}
//		List<MusicEntity> musicEntities = this.musicJpaRepository.findAll();
		List<MusicEntity> musicEntities = this.musicJpaRepository.findAllByDeleteIdIsNull();
		List<MusicDto> result = this.transfer(musicEntities);
		return result;
	}

	// role 이 USER 또는 ADMIN 인지 체크해서 아니면 null 을 리턴
	private IMember isUserOrAdmin(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember != null && signedMember.getRole().equals("GUEST") ) {
			return null;
		}
		return signedMember;
	}

	// role 이 ADMIN 인지 체크해서 아니면 null 을 리턴
	private IMember isAdmin(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember != null && !signedMember.getRole().equals("ADMIN") ) {
			return null;
		}
		return signedMember;
	}

	private List<MusicDto> transfer(List<MusicEntity> entityList) {
		List<MusicDto> result = entityList.stream().map( item -> (MusicDto)new MusicDto().copyMembers(item, true)).toList();
		return result;
	}
}
