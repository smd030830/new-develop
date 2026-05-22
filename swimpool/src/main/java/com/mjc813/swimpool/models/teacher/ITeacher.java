package com.mjc813.swimpool.models.teacher;

import com.mjc813.swimpool.models.swimpool.ISwimpool;

public interface ITeacher {
	Long getId();
	void setId(Long id);

	String getName();
	void setName(String name);

	String getMain();
	void setMain(String main);

	String getBirthYear();
	void setBirthYear(String birthYear);

	Long getSwimpoolId();
	void setSwimpoolId(Long swimpoolId);

	ISwimpool getSwimpool();
	void setSwimpool(ISwimpool swimpool);

	default ITeacher copy(ITeacher source, boolean forcedCopy) {
		if ( source == null ) {
			return this;
		}
		if ( forcedCopy || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( forcedCopy || source.getName() != null ) {
			this.setName(source.getName());
		}
		if ( forcedCopy || source.getMain() != null ) {
			this.setMain(source.getMain());
		}
		if ( forcedCopy || source.getBirthYear() != null ) {
			this.setBirthYear(source.getBirthYear());
		}
		if ( forcedCopy || source.getSwimpoolId() != null ) {
			this.setSwimpoolId(source.getSwimpoolId());
		}
		if ( forcedCopy || source.getSwimpool() != null ) {
			this.getSwimpool().copy(source.getSwimpool(), forcedCopy);
		}
		return this;
	}
}
