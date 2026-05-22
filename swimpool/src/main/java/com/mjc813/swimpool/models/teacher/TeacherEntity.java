package com.mjc813.swimpool.models.teacher;

import com.mjc813.swimpool.models.swimpool.ISwimpool;
import com.mjc813.swimpool.models.swimpool.SwimpoolDto;
import com.mjc813.swimpool.models.swimpool.SwimpoolEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "teacher")
public class TeacherEntity implements ITeacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 20, nullable = false)
	private String name;

	@Column(name = "main", length = 30, nullable = false)
	private String main;

	@Column(name = "birth_year", length = 4, nullable = true)
	private String birthYear;

	@Transient
	private Long swimpoolId;

	@JoinColumn(name = "swimpool_id", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private SwimpoolEntity swimpool;

	@Override
	public Long getSwimpoolId() {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.swimpool == null ) {
			this.swimpool = new SwimpoolEntity();
		}
		if ( this.swimpool.getId() != null) {
			this.swimpoolId = this.swimpool.getId();
		}
		return this.swimpool.getId();
	}

	@Override
	public void setSwimpoolId(Long swimpoolId) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.swimpool == null ) {
			this.swimpool = new SwimpoolEntity();
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
			this.swimpool = new SwimpoolEntity();
		}
		this.swimpool.copy(param, true);
		this.swimpoolId = this.swimpool.getId();
	}

//	@Override
//	public SwimpoolEntity getSwimpool() {
//		if ( this.swimpool == null ) {
//			this.swimpool = new SwimpoolEntity();
//		}
//		return this.swimpool;
//	}
}
