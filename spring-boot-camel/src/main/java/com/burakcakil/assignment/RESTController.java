package com.burakcakil.assignment;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @author: Burak Cakil
 *  
 *  RESTController is where we define the actual REST endpoint. It exposes the /location context path on default port 8080, 
 *  accepts GET requests and replies in application/json format. 
 *  It also looks for a  query parameter called address, but defaults it to a value if not found.
 *  The request is routed to a Camel Route endpoint called "direct:rest"  
 */

@RestController
public class RESTController {

	@Autowired
	ProducerTemplate pt;
	
    @RequestMapping(name="/location", method=RequestMethod.GET, produces = "application/json")
    public GeocodeResponse getLocation(@RequestParam(value="address", defaultValue="Istanbul") String address) {
        GeocodeResponse response = pt.requestBody("direct:rest",address, GeocodeResponse.class);
    	return response;
    }
    
    // The response is in GeocodeResponse format. Jsonpath captures this and resolves this object automatically into a JSON format.
}
