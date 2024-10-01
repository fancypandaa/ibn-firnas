package com.ibn.firnas.locator.events;

import com.ibn.firnas.locator.LocatorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class GeoEventListener implements ApplicationListener<GeoEvent> {
    private static Logger log = LoggerFactory.getLogger(GeoEventListener.class);
    @Autowired
    LocatorHandler locatorHandler;
    @Override
    @Async
    public void onApplicationEvent(GeoEvent event) {
        log.info("Custom Event Received: " + event.geoIP.toString());
        locatorHandler.saveCurrentLocation(event.geoIP);
    }
}
