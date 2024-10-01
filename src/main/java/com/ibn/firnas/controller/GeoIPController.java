package com.ibn.firnas.controller;

import com.ibn.firnas.locator.RawDBDemoGeoIPLocationService;
import com.ibn.firnas.locator.GeoIP;
import com.ibn.firnas.locator.events.GeoEventPublisher;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/GeoIPTest")
public class GeoIPController {
    private final RawDBDemoGeoIPLocationService locationService;
    private final GeoEventPublisher geoEventPublisher;
    public GeoIPController(GeoEventPublisher geoEventPublisher) throws IOException {
        this.geoEventPublisher = geoEventPublisher;
        locationService = new RawDBDemoGeoIPLocationService();
    }
    @PostMapping
    public ResponseEntity<GeoIP> getLocation(@RequestParam(value="ipAddress",required = true) String ipAddress) throws IOException, GeoIp2Exception {
        return new ResponseEntity<>(locationService.getLocation(ipAddress), HttpStatus.OK);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<String> locationListener(@PathVariable Long userId,@RequestBody GeoIP geoIP) throws IOException, GeoIp2Exception {
        geoIP.setUserId(userId);
        geoEventPublisher.publishLocationEvent(geoIP);
        return ResponseEntity.ok("Location published successfully!");
    }
}
