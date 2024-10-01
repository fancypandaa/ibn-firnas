package com.ibn.firnas.locator;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.repostiories.LocationRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;
import java.io.IOException;

@Component
public class LocatorHandler {
    private static Logger log = LoggerFactory.getLogger(LocatorHandler.class);
    private final UserDetailsRepository userRepository;
    private final LocationRepository locationRepository;
    private final RawDBDemoGeoIPLocationService rawGeoService;

    public LocatorHandler(UserDetailsRepository userRepository, LocationRepository locationRepository, RawDBDemoGeoIPLocationService rawGeoService) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.rawGeoService = rawGeoService;
    }
    public void saveCurrentLocation(GeoIP geoIP){
        try {
            Optional<UserDetails> optionalUser = userRepository.findById(geoIP.getUserId());
            if(!optionalUser.isPresent()){
                log.info("Spam or invalid userId");
                return;
            }
            GeoIP newGeoIp= getGeoFromIPAddress(geoIP.getIpAddress());
            if(isLocationValid(geoIP,newGeoIp)){
                Location newLocation = locationCreator(optionalUser.get(),newGeoIp);
                locationRepository.save(newLocation);
            }
            else {
                // call again with event 2
                log.info(newGeoIp.toString()+" Operation was failed!!!");
            }
        }
        catch (IOException | GeoIp2Exception exception){
            log.error(exception.getMessage());
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }

    }
    private GeoIP getGeoFromIPAddress(String ipAddress) throws IOException, GeoIp2Exception{
        GeoIP geoIP = rawGeoService.getLocation(ipAddress);
        return geoIP;
    }
    private Boolean isLocationValid(GeoIP geoIP,GeoIP newGeoIp) {
        if(newGeoIp.getCity() != null && newGeoIp.getCountry() !=null){
            if(geoIP.getLatitude().equals(newGeoIp.getLatitude())
                    && geoIP.getLongitude().equals(newGeoIp.getLongitude())){
                log.info(newGeoIp.getCountry()+ " " +newGeoIp.getCity());
                return true;
            }
        }
        return false;
    }
    private Location locationCreator(UserDetails userDetails,GeoIP geoIP){
        Instant cur = Instant.now();
        Location location=new Location();
        location.setUserDetails(userDetails);
        location.setIpAddress(geoIP.getIpAddress());
        location.setLat(geoIP.getLatitude());
        location.setLng(geoIP.getLongitude());
        location.setCountry(geoIP.getCountry());
        location.setIsActive(true);
        location.setTime(cur.toString());
        return location;
    }
}
