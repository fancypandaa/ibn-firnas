package com.ibn.firnas.locator.events;

import com.ibn.firnas.locator.GeoIP;
import org.springframework.context.ApplicationEvent;

public class GeoEvent extends ApplicationEvent {
    public GeoIP geoIP;

    public GeoEvent(Object source, GeoIP geoIP) {
        super(source);
        this.geoIP = geoIP;
    }

    public GeoEvent(Object source) {
        super(source);
    }
}
