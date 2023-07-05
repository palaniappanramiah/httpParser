package com.broadcom.parser.httpParser.controller;

import com.broadcom.parser.httpParser.model.HTTPResponse;
import com.broadcom.parser.httpParser.service.HTTPParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parser")
public class HTTPParserController {

    private final HTTPParserService httpParserService;
    @Autowired
    public HTTPParserController(HTTPParserService httpParserService) {
        this.httpParserService = httpParserService;
    }

    @PostMapping("/parse")
    public HTTPResponse parse(@RequestBody String httpResponse) {

        HTTPResponse response = httpParserService.parseResponse(httpResponse);

        if(response != null) {
            System.out.println("httpVersion    : " + response.getHttpVersion());
            System.out.println("statusCode     : " + response.getStatusCode());
            System.out.println("reasonPhrase   : " + response.getReasonPhrase());
            System.out.println("validHeaders   : " + response.getValidHeaders());
            System.out.println("invalidHeaders : " + response.getInvalidHeaders());
        }

        return response;
    }
}