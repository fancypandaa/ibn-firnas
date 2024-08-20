package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.exception.CustomException;

public interface FlightService {
    FlightDTO findFlightsById(Long flightId) throws CustomException;
    FlightDTO createNewFlight(FlightDTO flightDTO) throws CustomException;
    FlightDTO updateExistsFlight(Long flightId, FlightDTO flightDTO) throws CustomException;
}
