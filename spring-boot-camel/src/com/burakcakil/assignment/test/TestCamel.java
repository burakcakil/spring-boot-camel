package com.burakcakil.assignment.test;

import static org.junit.Assert.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.burakcakil.assignment.LocationRoute;

public class TestCamel extends CamelTestSupport {

	
	 @EndpointInject(uri = "mock:result")
	    protected MockEndpoint resultEndpoint;

	 
	@Produce(uri = "direct:firstRoute")
    protected ProducerTemplate template;
	
	@Test
	public void configure() throws Exception {
		String response = template.requestBody("direct:firstRoute","Istanbul", String.class);
		assertEquals(response.contains("stanbul"),true);
	}
	

	
	@Override
    protected RouteBuilder createRouteBuilder() {
        return new LocationRoute();
    };
}


