package com.mjc813.swimpool.models.teacher;

import com.mjc813.swimpool.models.swimpool.ISwimpool;
import com.mjc813.swimpool.models.swimpool.SwimpoolDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TeacherDto implements ITeacher {
	private Long id;
	private String name;
	private String main;
	private String birthYear;
	private Long swimpoolId;
	private SwimpoolDto swimpool;

	@Override
	public Long getSwimpoolId() {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.swimpool == null ) {
			this.swimpool = new SwimpoolDto();
		}
		this.swimpool.setId(this.swimpoolId);
		return this.swimpool.getId();
	}

	@Override
	public void setSwimpoolId(Long swimpoolId) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.swimpool == null ) {
			this.swimpool = new SwimpoolDto();
		}
		this.swimpool.setId(this.swimpoolId);
		this.swimpoolId = swimpoolId;
	}

	@Override
	public void setSwimpool(ISwimpool param) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( param == null ) {
			return;
		}
		if ( this.swimpool == null ) {
			this.swimpool = new SwimpoolDto();
		}
		this.swimpool.copy(param, true);
		this.swimpoolId = this.swimpool.getId();
	}

//	@Override
//	public SwimpoolDto getSwimpool() {
//		if ( this.swimpool == null ) {
//			this.swimpool = new SwimpoolDto();
//		}
//		return this.swimpool;
//	}
}
