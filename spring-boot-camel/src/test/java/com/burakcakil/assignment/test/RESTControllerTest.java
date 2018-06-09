package com.burakcakil.assignment.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.burakcakil.assignment.GeocodeResponse;
import com.burakcakil.assignment.RESTController;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(RESTController.class)
public class RESTControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RESTController restController;
	
	@Test
	public void testGetLocation() throws Exception{
		GeocodeResponse response = new GeocodeResponse();
		response.setAddress("Istanbul, Turkey");
		response.setLat("41.0082376");
		response.setLng("28.9783589");
		response.setStatus("OK");
		
		given(restController.getLocation("Istanbul")).willReturn(response);
		
		this.mvc.perform(get("/location").requestAttr("address", "Istanbul").contentType(APPLICATION_JSON)).andExpect(status().isOk());
		
	}

}
