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

@Component
public class LocationRoute extends RouteBuilder {

   @Override
   public void configure() throws Exception {
      JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(JAXBContextFactory.createContext(new Class[]{GeocodeResponse.class},null));
      JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
      jacksonDataFormat.setPrettyPrint(true);
      jacksonDataFormat.enableFeature(SerializationFeature.WRAP_ROOT_VALUE);

      from("direct:firstRoute")
      		.setHeader(Exchange.HTTP_QUERY,body().prepend("address="))
            .setHeader(Exchange.CONTENT_TYPE,constant("application/json"))
            .to("http4:maps.googleapis.com/maps/api/geocode/xml?key=AIzaSyDucnted4Cg19cvWLYQaeYMkI2g8-2iWtQ")
            .unmarshal(jaxbDataFormat)
            .marshal(jacksonDataFormat);
   }
}
