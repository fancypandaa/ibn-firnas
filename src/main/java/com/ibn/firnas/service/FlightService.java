package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.FlightAllDTO;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import java.util.List;
public interface FlightService {
    FlightDTO findFlightsById(Long flightId);
    FlightAllDTO findFlightAndCrewById(Long flightId);
    FlightDTO createNewFlight(FlightDTO flightDTO);
    FlightDTO createNewFlightWithCrew(FlightDTO  flightAllDTO);
    FlightDTO updateExistsFlight(Long flightId, FlightDTO flightDTO);
    FlightDTO assignFlightCrew(Long flightId, List<Long> userIds);
}
