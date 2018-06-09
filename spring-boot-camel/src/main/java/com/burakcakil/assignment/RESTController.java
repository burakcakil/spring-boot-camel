package com.burakcakil.assignment;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

	@Autowired
	ProducerTemplate pt;
	
    @RequestMapping(name="/location", method=RequestMethod.GET, produces = "application/json")
    public GeocodeResponse getLocation(@RequestParam(value="address", defaultValue="Istanbul") String address) {
        GeocodeResponse response = pt.requestBody("direct:firstRoute",address, GeocodeResponse.class);
    	return response;
    }
}
