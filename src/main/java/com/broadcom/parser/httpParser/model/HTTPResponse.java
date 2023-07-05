package com.broadcom.parser.httpParser.model;

import java.util.HashMap;
import java.util.Map;

public class HTTPResponse {
    private String httpVersion;
    private int statusCode;
    private String reasonPhrase;
    private Map<String, String> headers;
    private int validHeaders;
    private int invalidHeaders;

    public HTTPResponse() {
        headers = new HashMap<>();
        validHeaders = 0;
        invalidHeaders = 0;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getValidHeaders() {
        return validHeaders;
    }

    public void setValidHeaders(int validHeaders) {
        this.validHeaders = validHeaders;
    }

    public int getInvalidHeaders() {
        return invalidHeaders;
    }

    public void setInvalidHeaders(int invalidHeaders) {
        this.invalidHeaders = invalidHeaders;
    }
}