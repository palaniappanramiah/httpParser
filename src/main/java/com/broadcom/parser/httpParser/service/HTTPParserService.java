package com.broadcom.parser.httpParser.service;

import com.broadcom.parser.httpParser.model.HTTPResponse;
import org.springframework.stereotype.Service;

@Service
public class HTTPParserService {

    HTTPResponse httpResponse;

    public HTTPResponse parseResponse(String response) {
        httpResponse = new HTTPResponse();
        String[] lines = response.split("\r\n");
        if (lines.length < 1) {
            System.out.println("Invalid response: No status line found");
            return null;
        }

        parseStatusLine(lines[0]);

        for (int i = 1; i < lines.length; i++) {
            if (!lines[i].trim().isEmpty()) {
                parseHeaderLine(lines[i]);
            }
        }
        return httpResponse;
    }

    private void parseStatusLine(String line) {

        String[] parts = line.split(" ");
        if (parts.length != 3 || !parts[0].startsWith("HTTP/")) {
            System.out.println("Invalid status line");
            return;
        }
        String[] version = parts[0].split("/");
        httpResponse.setHttpVersion(version[1]);
        httpResponse.setStatusCode(Integer.parseInt(parts[1]));
        httpResponse.setReasonPhrase(parts[2]);
    }

    private void parseHeaderLine(String line) {
        String[] headerParts = line.split(": ");
        if (headerParts.length != 2) {
            httpResponse.setInvalidHeaders(httpResponse.getInvalidHeaders() + 1);
            return;
        }

        String headerName = headerParts[0].trim();
        String headerValue = headerParts[1].trim();
        httpResponse.setValidHeaders(httpResponse.getValidHeaders() + 1);
    }
}
