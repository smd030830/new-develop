package com.mjc813.swimpool.models.swimpool;

public interface ISwimpool {
	Long getId();
	void setId(Long id);

	String getName();
	void setName(String name);

	String getTel();
	void setTel(String tel);

	String getAddr1();
	void setAddr1(String addr1);

	String getAddr2();
	void setAddr2(String addr2);

	String getLanes();
	void setLanes(String lanes);

	String getSize();
	void setSize(String size);

	default ISwimpool copy(ISwimpool source, boolean forcedCopy) {
		if ( source == null ) {
			return this;
		}
		if ( forcedCopy || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( forcedCopy || source.getName() != null ) {
			this.setName(source.getName());
		}
		if ( forcedCopy || source.getTel() != null ) {
			this.setTel(source.getTel());
		}
		if ( forcedCopy || source.getAddr1() != null ) {
			this.setAddr1(source.getAddr1());
		}
		if ( forcedCopy || source.getAddr2() != null ) {
			this.setAddr2(source.getAddr2());
		}
		if ( forcedCopy || source.getLanes() != null ) {
			this.setLanes(source.getLanes());
		}
		if ( forcedCopy || source.getSize() != null ) {
			this.setSize(source.getSize());
		}
		return this;
	}
}
