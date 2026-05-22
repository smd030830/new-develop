package com.mjc813.petapp.pet.svc;

import com.mjc813.petapp.pet.PetRequestDto;
import com.mjc813.petapp.pet.dto.PetDto;
import com.mjc813.petapp.pet.dto.PetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;

	public PetDto insert(PetDto petDto) {
		PetEntity petEntity = new PetEntity();
		petEntity.copyMemberValue(petDto);
		this.petRepository.save(petEntity);
		PetDto result = new PetDto();
		result.copyMemberValue(petEntity);
		return result;
	}

	public PetDto update(PetDto petDto) {
//		PetEntity petEntity = new PetEntity();
		PetEntity petEntity = this.petRepository.findById(petDto.getId()).orElseThrow();
//		1. findById(id) jpa 메소드로 id 의 행 객체를 가져온다.
		petEntity.copyMemberValue(petDto);
		// 2. 가져온 객체에서 원하는 컬럼의 값을 수정한다. 다만 id를 수정하면 절대 안된다.

		this.petRepository.save(petEntity);
		// 3. save 를 한다.

		PetDto result = new PetDto();
		result.copyMemberValue(petEntity);
		return result;
	}

	public PetDto deleteById(int id) {
		PetEntity petEntity = this.petRepository.findById(id).orElseThrow();
		PetDto result = new PetDto();
		result.copyMemberValue(petEntity);
		this.petRepository.deleteById(id);
		return result;
	}

	public PetDto findById(int id) {
		PetEntity petEntity = this.petRepository.findById(id).orElseThrow();
		PetDto result = new PetDto();
		result.copyMemberValue(petEntity);
		return result;
	}

	public Slice<PetEntity> findByNameContains(PetRequestDto requestDto, Pageable pageable) {
		Slice<PetEntity> list = this.petRepository.findByNameContains(requestDto.getSearchName(), pageable);
		return list;
	}
}
