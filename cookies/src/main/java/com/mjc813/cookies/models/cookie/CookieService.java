package com.mjc813.cookies.models.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookieService {
	@Autowired
	private CookieRepository cookieRepository;

	public CookieDto insert(CookieDto insertDto) {
		CookieEntity CookieEntity = (CookieEntity)new CookieEntity().copyMembers(insertDto, true);
		CookieEntity.setId(null);
		CookieEntity save = this.cookieRepository.save(CookieEntity);
		CookieDto result = (CookieDto)new CookieDto().copyMembers(save, true);
		return result;
	}

	public CookieDto update(CookieDto updateDto) {
		CookieEntity find = this.cookieRepository.findById(updateDto.getId()).orElseThrow();
		CookieEntity updateEntity = (CookieEntity)new CookieEntity().copyMembers(find, true);
		updateEntity.copyMembers(updateDto, false); // false 이유는 수정할 멤버변수값만 복사한다.
		CookieEntity save = this.cookieRepository.save(updateEntity);
		CookieDto result = (CookieDto)new CookieDto().copyMembers(save, true);
		return result;
	}

	public CookieDto findById(Long id) {
		CookieEntity find = this.cookieRepository.findById(id).orElseThrow();
		CookieDto result = (CookieDto)new CookieDto().copyMembers(find, true);
		return result;
	}

	public CookieDto deleteById(Long id) {
		CookieDto find = this.findById(id);
		this.cookieRepository.deleteById(id);
		return find;
	}

	public Slice<CookieDto> findByNameContains(String name, Pageable pageable) {
		Slice<CookieEntity> slc = this.cookieRepository.findByNameContains(name, pageable);
		List<CookieDto> list = slc.getContent().stream()
				.map( t -> (CookieDto)new CookieDto().copyMembers(t, true))
				.toList();
		Slice<CookieDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}
}
