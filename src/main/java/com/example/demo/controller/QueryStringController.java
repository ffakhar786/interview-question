package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QueryStringService;
/** Represents a QueryString URL Controller.
 * @author Fakhar Mumtaz
 * @version 1.0
 * @since 1.0
*/
@RestController
public class QueryStringController {
	Logger logger = LogManager.getLogger(QueryStringController.class);

	@Autowired
	private QueryStringService queryStringService;
	
	/** Gets encodes the URL .
	 * @return string representing the original URL 
	*/
	@GetMapping("/short")
	@ResponseBody
    public String encodeQueryString(@RequestParam(name = "url", required = true, defaultValue = "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json#38") String url) {
    	String tinyStr="";
    	//tinyStr = queryStringService.encodeQueryStringUrl(url);
    	logger.trace("invoking service method");
    	tinyStr = queryStringService.encodeUrlBase64(url);
        return tinyStr;
    }
	
	/** Gets decodes the encoded URL representation 
	 * @return string the original URL 
	*/
	@GetMapping("/long")
    public String decodeQueryString(@RequestParam(name = "tiny") String tiny) {
    	String url = "";
    	//url = queryStringService.decodeQueryStringUrl(tiny);
    	url = queryStringService.decodeUrlBase64(tiny);
        return url;
    }
}
