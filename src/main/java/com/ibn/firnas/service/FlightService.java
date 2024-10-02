package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.FlightDTO;

public interface FlightService {
    FlightDTO findFlightsById(Long flightId);
    FlightDTO createNewFlight(FlightDTO flightDTO);
    FlightDTO updateExistsFlight(Long flightId, FlightDTO flightDTO);
}
