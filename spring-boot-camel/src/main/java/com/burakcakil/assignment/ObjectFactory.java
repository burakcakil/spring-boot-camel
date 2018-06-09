package com.burakcakil.assignment;

import javax.xml.bind.annotation.XmlRegistry;

/*
 *  @author: Burak Cakil
 *  
 *  ObjectFactory class is used by JAXB to find the class to map the XML to. 
 */

@XmlRegistry
public class ObjectFactory {

    public GeocodeResponse createLocation() {
        return new GeocodeResponse();
    }

}