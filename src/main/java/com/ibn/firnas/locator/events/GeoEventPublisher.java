package com.ibn.firnas.locator.events;

import com.ibn.firnas.locator.GeoIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class GeoEventPublisher {
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public void publishLocationEvent(GeoIP geoIP){
        GeoEvent geoEvent = new GeoEvent(this,geoIP);
        eventPublisher.publishEvent(geoEvent);
    }
}
