package com.ibn.firnas.service;

import com.ibn.firnas.controller.FlightController;
import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.dto.mapper.FlightMapper;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.repostiories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.*;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{
    private static Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDTO findFlightsById(Long flightId) throws CustomException {
        Optional<Flight> optionalFlight=flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            throw new CustomException("Flight Not found for "+flightId);
        }
        return flightMapper.flightToFlightDTO(optionalFlight.get());
    }

    @Override
    public FlightDTO createNewFlight(FlightDTO flightDTO) throws CustomException{
        if(Stream.of(flightDTO.tripCategory(),
                flightDTO.departureId(),flightDTO.arrivalId(),flightDTO.duration(),flightDTO.outbound_date()).allMatch(Objects::isNull)){
            throw new CustomException("Make sure that values not Null [Trip Category, departure, arrival, duration and outbound]");
        }
        Flight savedFlight=flightRepository.save(flightMapper.flightDTOtoFlight(flightDTO));
        return flightMapper.flightToFlightDTO(savedFlight);
    }

    @Override
    public FlightDTO updateExistsFlight(Long flightId, FlightDTO flightDTO) throws CustomException {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            throw new CustomException("Current flight not exists"+flightId);
        }
        Flight flight = optionalFlight.get();
        if(checkNewFlightValues(flightDTO.tripCategory())){
            flight.setTripCategory(flightDTO.tripCategory());
        }
        if(checkNewFlightValues(flightDTO.departureId())){
            flight.setDepartureId(flightDTO.departureId());
        }
        if(checkNewFlightValues(flightDTO.arrivalId())){
            flight.setArrivalId(flightDTO.arrivalId());
        }
        if(checkNewFlightValues(flightDTO.outbound_date())){
            flight.setOutbound_date(flightDTO.outbound_date());
        }
        if(checkNewFlightValues(flightDTO.stops())){
            flight.getStops().putAll(flightDTO.stops());
        }
        if(checkNewFlightValues(flightDTO.landingTime()) &&
                !flightDTO.landingTime().equals(flight.getLandingTime())){
            flight.setLandingTime(flightDTO.landingTime());
        }
        if(checkNewFlightValues(flightDTO.takeOffTime())&&
                !flightDTO.takeOffTime().equals(flight.getTakeOffTime())){
            flight.setTakeOffTime(flightDTO.takeOffTime());
        }
        if(checkNewFlightValues(flightDTO.airportTakeOff())  &&
                !flightDTO.airportTakeOff().equals(flight.getAirportTakeOff())){
            flight.setAirportTakeOff(flightDTO.airportTakeOff());
        }
        if(checkNewFlightValues(flightDTO.airportLanding()) &&
                !flightDTO.airportLanding().equals(flight.getAirportLanding())){
            flight.setAirportLanding(flightDTO.airportLanding());
        }
        return flightMapper.flightToFlightDTO(flightRepository.save(flight));
    }
    private <T> boolean checkNewFlightValues(T t){
        return  (t != null) ? true:false;
    }
}
