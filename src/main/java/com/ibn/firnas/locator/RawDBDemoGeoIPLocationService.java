package com.ibn.firnas.locator;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
@Component
public class RawDBDemoGeoIPLocationService {
    private DatabaseReader databaseReader;
    public RawDBDemoGeoIPLocationService()throws IOException {
        File database = new File("src/main/resources/static/GeoLite2-City.mmdb");
        databaseReader =new DatabaseReader.Builder(database).build();
    }
    public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception{
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = databaseReader.city(ipAddress);
        String cityName = response.getCity().getName();
        String countryName =response.getCountry().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new GeoIP(ip,countryName,cityName,latitude,longitude);
    }
}
