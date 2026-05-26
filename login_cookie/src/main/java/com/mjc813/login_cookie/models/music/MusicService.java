package com.mjc813.login_cookie.models.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {
	@Autowired
	private MusicJpaRepository musicJpaRepository;

	public MusicDto insert(MusicDto insertDto) {
		MusicEntity musicEntity = (MusicEntity)new MusicEntity().copyMembers(insertDto, true);
		musicEntity.setId(null);
		MusicEntity saved = this.musicJpaRepository.save(musicEntity);
		MusicDto musicDto = (MusicDto)new MusicDto().copyMembers(saved, true);
		return musicDto;
	}

	public MusicDto findById(Long id) {
		MusicEntity musicEntity = this.musicJpaRepository.findById(id).orElseThrow();
		MusicDto find = (MusicDto)new MusicDto().copyMembers(musicEntity, true);
		return find;
	}

	public List<MusicDto> findAll() {
		List<MusicEntity> musicEntities = this.musicJpaRepository.findAll();
		List<MusicDto> result = this.transfer(musicEntities);
		return result;
	}

	private List<MusicDto> transfer(List<MusicEntity> entityList) {
		List<MusicDto> result = entityList.stream().map( item -> (MusicDto)new MusicDto().copyMembers(item, true)).toList();
		return result;
	}
}
