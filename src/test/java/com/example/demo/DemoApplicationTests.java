package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
	private final String urlLong = "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json#38";
	private String urlShort = "";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting?name=David", String.class))
				.contains("Hello World");
	}

	@Test
	public void encodeUrlBase64() throws Exception {
		urlShort = this.restTemplate.getForObject("http://localhost:" + port + "/short?url=" + urlLong, String.class);
		String urlLongReturned = this.restTemplate.getForObject("http://localhost:" + port + "/long?tiny=" + urlShort,
				String.class);
		assertThat(urlLong).contains(urlLongReturned);
	}

	@Test
	public void encodeQueryString() throws Exception {
		urlShort = this.restTemplate.getForObject("http://localhost:" + port + "/short-url?url=" + urlLong,
				String.class);
		String urlLongReturned = this.restTemplate
				.getForObject("http://localhost:" + port + "/long-url?tiny=" + urlShort, String.class);
		assertThat(urlLong).contains(urlLongReturned);
	}
}