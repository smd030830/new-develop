package com.mjc813.cookies.biz.file;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestFileUtil {
	private FileUtil fileUtil = new FileUtil();

	@Test
	public void testGetExtension() {
		String ext1 = this.fileUtil.getExtension("1111");
		assertThat(ext1).isNotNull();
		assertThat(ext1).isEqualTo("");

		ext1 = this.fileUtil.getExtension("1111.112.jpg");
		assertThat(ext1).isNotNull();
		assertThat(ext1).isEqualTo("jpg");

		ext1 = this.fileUtil.getExtension("aaa.112.jpg.JPEG");
		assertThat(ext1).isNotNull();
		assertThat(ext1).isEqualTo("jpeg");
	}

	@Test
	public void testDirectories() {
		// 디렉토리 생성해보고 존재하는지 체크
		// 디렉토리 삭제하고 존재하는지 체크
	}
}
