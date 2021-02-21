package net.chomookun.apps.sdk.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdGeneratorTest {

	@Test
	public void testUuid() throws Exception {
		String uuid = IdGenerator.uuid();
		log.info("uuid:{}", uuid);
	}

	@Test
	public void testMd5() throws Exception {
		String md5 = IdGenerator.md5("abcd1234!@#$");
		log.info("md5:{}", md5);
	}

	@Test
	public void testBase64() throws Exception {
		String plainValue = "abcd1234!@#$한글테스트";
		String encodedValue = IdGenerator.encodeBase64(plainValue);
		String decodedValue = IdGenerator.decodeBase64(encodedValue);
		assertEquals(plainValue, decodedValue);
	}

}
