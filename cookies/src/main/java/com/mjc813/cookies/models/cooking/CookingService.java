package com.mjc813.cookies.models.cooking;

import com.mjc813.cookies.models.cookie.CookieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookingService {
	@Autowired
	private CookingRepository cookingRepository;

	public CookingDto insert(CookingDto insertDto) {
		CookingEntity newEntity = (CookingEntity)new CookingEntity().copyMembers(insertDto, true);
		newEntity.setId(null);
		CookingEntity save = this.cookingRepository.save(newEntity);
		CookingDto result = (CookingDto)new CookingDto().copyMembers(save, true);
		return result;
	}

	public CookingDto update(CookingDto updateDto) {
		CookingEntity find = this.cookingRepository.findById(updateDto.getId()).orElseThrow();
		CookingEntity updateEntity = (CookingEntity)new CookingEntity().copyMembers(find, true);
		updateEntity.copyMembers(updateDto, false);
		CookingEntity save = this.cookingRepository.save(updateEntity);
		CookingDto result = (CookingDto)new CookingDto().copyMembers(save, true);
		return result;
	}

	public CookingDto findById(Long id) {
		CookingEntity find = this.cookingRepository.findById(id).orElseThrow();
		CookingDto result = (CookingDto)new CookingDto().copyMembers(find, true);
		return result;
	}

	public CookingDto deleteById(Long id) {
		CookingDto find = this.findById(id);
		this.cookingRepository.deleteById(id);
		return find;
	}

	public Slice<CookingDto> findAllByDescriptionContaining(String description, Pageable pageable) {
		Slice<CookingEntity> slc = this.cookingRepository.findAllByDescriptionContaining(description, pageable);
		List<CookingDto> list = slc.getContent().stream()
				.map( x -> (CookingDto) new CookingDto().copyMembers(x, true))
				.toList();
		Slice<CookingDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}

	public Slice<CookingDto> findAllByCookieEquals(Long cookieId, Pageable pageable) {
		CookieEntity cookie = CookieEntity.builder().id(cookieId).build();
		Slice<CookingEntity> slc = this.cookingRepository.findAllByCookieEquals(cookie, pageable);
		List<CookingDto> list = slc.getContent().stream()
				.map( x -> (CookingDto) new CookingDto().copyMembers(x, true))
				.toList();
		Slice<CookingDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}
}
