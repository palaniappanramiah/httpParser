package com.broadcom.parser.httpParser.model;

public class HTTPResponse {
    private String httpVersion;
    private int statusCode;
    private String reasonPhrase;
    private int validHeaders;
    private int invalidHeaders;

    public HTTPResponse() {
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