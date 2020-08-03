package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/** Represents a greeting Controller. 
 * @author Backbase.com
 * @version 
 * @since 
*/
@RestController
public class GreetingController {

	/** It checks if the name is David, then it returns "Hello World"
	 * @param name
	 * @return
	 */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
    	if("David".equals(name)) 
    		return "Hello World";
        return "greeting " + name;
    }


}
