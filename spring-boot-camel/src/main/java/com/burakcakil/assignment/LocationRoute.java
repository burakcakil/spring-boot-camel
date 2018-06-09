package com.burakcakil.assignment;



import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;

/*
 *  @author: Burak Cakil
 *  
 *  LocationRoute class is used to define the Camel Route. 
 */
@Component
public class LocationRoute extends RouteBuilder {

   @Override
   public void configure() throws Exception {
      JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(JAXBContextFactory.createContext(new Class[]{GeocodeResponse.class},null));
      JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
      jacksonDataFormat.setPrettyPrint(true);
      jacksonDataFormat.enableFeature(SerializationFeature.WRAP_ROOT_VALUE);

      // Consumes from "endpoint direct:rest", sets headers for the request and routes the request to Google API using http4

      from("direct:rest")
      		.setHeader(Exchange.HTTP_QUERY,body().prepend("address="))
            .setHeader(Exchange.CONTENT_TYPE,constant("application/json"))
            .to("http4:maps.googleapis.com/maps/api/geocode/xml?key=AIzaSyDucnted4Cg19cvWLYQaeYMkI2g8-2iWtQ")
            // The request tells Google API that the response should be in XML format.
            .unmarshal(jaxbDataFormat);
            //.marshal(jacksonDataFormat);
      
      // The response is marshaled into a GeocodeResponse object using JAXB. 
      // It could also be unmarshaled into a JSON format, but I left it to the REST component, it does that automatically.
   }
}
