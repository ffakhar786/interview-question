package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QueryStringService;

@RestController
public class QueryStringController {
	
	@Autowired
	private QueryStringService queryStringService;
	
	@GetMapping("/short")
	@ResponseBody
    public String encodeQueryString(@RequestParam(name = "url", required = true, defaultValue = "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json#38") String url) {
    	String tinyStr="";
    	tinyStr = queryStringService.encodeQueryStringUrl(url);
        return tinyStr;
    }
	
	@GetMapping("/long")
    public String decodeQueryString(@RequestParam(name = "tiny") String tiny) {
    	String url = "";
    	url = queryStringService.decodeQueryStringUrl(tiny);
        return url;
    }
}
