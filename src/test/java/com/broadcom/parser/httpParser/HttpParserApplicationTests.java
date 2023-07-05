package com.broadcom.parser.httpParser;

import com.broadcom.parser.httpParser.controller.HTTPParserController;
import com.broadcom.parser.httpParser.model.HTTPResponse;
import com.broadcom.parser.httpParser.service.HTTPParserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class HttpParserApplicationTests {

	@Test
	public void testValidResponse() {
		HTTPParserService parser = new HTTPParserService();
		String response = "HTTP/1.0 200 OK\r\n" +
				"cache-control: public\r\n" +
				"content-length: 0\r\n" +
				"content-type: image/svg+xml\r\n" +
				"date: Tue, 22 Jun 2021 22:24:42 GMT";
		HTTPResponse httpResponse = parser.parseResponse(response);

		assertEquals("1.0", httpResponse.getHttpVersion());
		assertEquals(200, httpResponse.getStatusCode());
		assertEquals("OK", httpResponse.getReasonPhrase());
		assertEquals(4, httpResponse.getValidHeaders());
		assertEquals(0, httpResponse.getInvalidHeaders());
	}

	@Test
	public void testInvalidStatusLine() {
		HTTPParserService parser = new HTTPParserService();
		String response = "Invalid status line";
		HTTPResponse httpResponse = parser.parseResponse(response);

		assertNull(httpResponse.getHttpVersion());
		assertEquals(0, httpResponse.getStatusCode());
		assertNull(httpResponse.getReasonPhrase());
		assertEquals(0, httpResponse.getValidHeaders());
		assertEquals(0, httpResponse.getInvalidHeaders());
	}

	@Test
	public void testInvalidHeaders() {
		HTTPParserService parser = new HTTPParserService();
		String response = "HTTP/1.1 302 Found\r\n" +
				"cache-control: public\r\n" +
				"Transfer-encoding: chunked\r\n" +
				"invalid_header\r\n" +
				"date: Tue, 22 Jun 2021 22:24:42 GMT";
		HTTPResponse httpResponse = parser.parseResponse(response);

		assertEquals("1.1", httpResponse.getHttpVersion());
		assertEquals(302, httpResponse.getStatusCode());
		assertEquals("Found", httpResponse.getReasonPhrase());
		assertEquals(3, httpResponse.getValidHeaders());
		assertEquals(1, httpResponse.getInvalidHeaders());
	}

	@Test
	public void testMissingStatusLineInController() {
		HTTPParserService parser = new HTTPParserService();
		String response = " ";
		HTTPParserController parserController = new HTTPParserController(parser);
		HTTPResponse httpResponse = parserController.parse(response);

		assertNull(httpResponse.getHttpVersion());
		assertEquals(0, httpResponse.getStatusCode());
		assertNull(httpResponse.getReasonPhrase());
		assertEquals(0, httpResponse.getValidHeaders());
		assertEquals(0, httpResponse.getInvalidHeaders());
	}

	@Test
	public void testMissingHeadersInController() {
		HTTPParserService parser = new HTTPParserService();
		String response = "HTTP/1.1 200 OK";
		HTTPParserController parserController = new HTTPParserController(parser);
		HTTPResponse httpResponse = parserController.parse(response);

		assertEquals("1.1", httpResponse.getHttpVersion());
		assertEquals(200, httpResponse.getStatusCode());
		assertEquals("OK", httpResponse.getReasonPhrase());
		assertEquals(0, httpResponse.getValidHeaders());
		assertEquals(0, httpResponse.getInvalidHeaders());
	}
}