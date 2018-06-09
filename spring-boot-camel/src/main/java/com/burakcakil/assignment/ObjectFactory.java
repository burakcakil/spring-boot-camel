package com.burakcakil.assignment;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public GeocodeResponse createLocation() {
        return new GeocodeResponse();
    }

}