package com.mjc813.jwtsecurity_login.models.music;

import com.mjc813.jwtsecurity_login.common.LoginException;
import com.mjc813.jwtsecurity_login.common.Mjc813Exception;
import com.mjc813.jwtsecurity_login.common.ResponseCode;
import com.mjc813.jwtsecurity_login.models.member.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
	@Autowired
	private MusicJpaRepository musicJpaRepository;

	public MusicDto insert(MusicDto insertDto) throws LoginException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object obj = authentication.getPrincipal();
		if ( obj instanceof IMember signedMember ) {
			MusicEntity musicEntity = (MusicEntity)new MusicEntity().copyMembers(insertDto, true);
			musicEntity.setId(null);
			musicEntity.setCreateId(signedMember.getSignId());
			musicEntity.setCreateDt(LocalDateTime.now());
			MusicEntity saved = this.musicJpaRepository.save(musicEntity);
			MusicDto musicDto = (MusicDto)new MusicDto().copyMembers(saved, true);
			return musicDto;
		} else {
			throw new LoginException("is not valid member");
		}
	}

	public MusicDto update(MusicDto updateDto) throws Mjc813Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		IMember signedMember = (IMember)authentication.getPrincipal();
//		MusicEntity findEntity = this.musicJpaRepository.findByIdAndDeleteIdIsNull(updateDto.getId()).orElseThrow();
//		MusicDto findDto = (MusicDto)new MusicDto().copyMembers(findEntity, true);
		MusicDto findDto = this.findById(updateDto.getId());    // id 로 자료를 찾는다.
		findDto.setUpdateId(signedMember.getSignId());
		findDto.setUpdateDt(LocalDateTime.now());
		findDto.copyMembers(updateDto, false);
		MusicEntity updateEntity = (MusicEntity)new MusicEntity().copyMembers(findDto, true);
		MusicEntity savedEntity = this.musicJpaRepository.save(updateEntity);
		MusicDto returnDto = (MusicDto)new MusicDto().copyMembers(savedEntity, true);
		return returnDto;
	}

	public boolean isCreateId(Long musidId, String signId) throws Mjc813Exception {
		MusicDto findMusic = this.findById(musidId);    // id 로 자료를 찾는다.
		if ( findMusic != null && findMusic.getCreateId().equals(signId) ) {
			return true;
		}
		return false;
	}

	public MusicDto deleteById(Long id) throws Mjc813Exception {
		MusicDto findDto = this.findById(id);    // id 로 자료를 찾는다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		IMember signedMember = (IMember)authentication.getPrincipal();
		findDto.setDeleteId(signedMember.getSignId());
		findDto.setDeleteDt(LocalDateTime.now());
		MusicEntity deleteEntity = (MusicEntity)new MusicEntity().copyMembers(findDto, true);
		MusicEntity savedEntity = this.musicJpaRepository.save(deleteEntity);
		MusicDto returnDto = (MusicDto)new MusicDto().copyMembers(savedEntity, true);
		return returnDto;
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

	public List<MusicDto> findAll() throws Mjc813Exception {
		// findAll 은 모든 자료를 조회해서 리턴하므로 자료의 갯수가 전부 20~30개 정도를 넘으면 별로 안좋은 기능이다.
		// 그 보다 많은 데이터를 조회하려면 Paging 이나 Slicing 으로 조회하세요
//		List<MusicEntity> musicEntities = this.musicJpaRepository.findAll();
		List<MusicEntity> musicEntities = this.musicJpaRepository.findAllByDeleteIdIsNull();
		List<MusicDto> result = this.transfer(musicEntities);
		return result;
	}

	private List<MusicDto> transfer(List<MusicEntity> entityList) {
		List<MusicDto> result = entityList.stream().map( item -> (MusicDto)new MusicDto().copyMembers(item, true)).toList();
		return result;
	}
}
