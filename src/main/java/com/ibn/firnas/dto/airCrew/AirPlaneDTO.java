package com.ibn.firnas.dto.airCrew;


import java.util.Date;

public record AirPlaneDTO( Long planeId,
         String companyName, String planeName,
         Integer airline, String airlineLogo,  Date createdAt, Date updateAt) {
}
