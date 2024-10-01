package com.ibn.firnas.locator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeoIP {
    private Long userId;
    private String ipAddress;
    private String city;
    private String country;
    private String latitude;
    private String longitude;

    public GeoIP(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public GeoIP(Long userId, String ipAddress, String latitude, String longitude) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoIP(String ipAddress, String city, String country, String latitude, String longitude) {
        this.ipAddress = ipAddress;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoIP{" +
                "userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
