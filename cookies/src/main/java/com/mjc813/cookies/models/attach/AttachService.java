package com.mjc813.cookies.models.attach;

import com.mjc813.cookies.models.cookie.CookieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachService {
	@Autowired
	private AttachRepository attachRepository;

	public AttachDto insert(AttachDto insertDto) {
		AttachEntity insertEntity = (AttachEntity)new AttachEntity().copyMembers(insertDto, true);
		insertEntity.setId(null);
		AttachEntity save = this.attachRepository.save(insertEntity);
		AttachDto result = (AttachDto)new AttachDto().copyMembers(save, true);
		return result;
	}

	public AttachDto update(AttachDto updateDto) {
		AttachEntity findEntity = this.attachRepository.findById(updateDto.getId()).orElseThrow();
		AttachEntity updateEntity = (AttachEntity)new AttachEntity().copyMembers(findEntity, true);
		updateEntity.copyMembers(updateDto, false);
		AttachEntity save = this.attachRepository.save(updateEntity);
		AttachDto result = (AttachDto)new AttachDto().copyMembers(save, true);
		return result;
	}

	public AttachDto findById(Long id) {
		AttachEntity findEntity = this.attachRepository.findById(id).orElseThrow();
		AttachDto result = (AttachDto)new AttachDto().copyMembers(findEntity, true);
		return result;
	}

	public AttachDto deleteById(Long id) {
		AttachDto result = this.findById(id);
		this.attachRepository.deleteById(id);
		return result;
	}

	public List<AttachDto> findAllByCookieId(Long cookieId) {
		CookieEntity cookieEntity = CookieEntity.builder().id(cookieId).build();
		List<AttachEntity> list = this.attachRepository.findAllByCookie(cookieEntity);
		List<AttachDto> result = list.stream().map(
				item -> (AttachDto)new AttachDto().copyMembers(item, true)
		).toList();
		return result;
	}
}
