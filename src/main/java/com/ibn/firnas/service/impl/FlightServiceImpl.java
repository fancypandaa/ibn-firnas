package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.dto.mapper.FlightMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.FlightRepository;
import com.ibn.firnas.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private static Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDTO findFlightsById(Long flightId) {
        Optional<Flight> optionalFlight=flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            log.error("Flight Not found : "+flightId);
            throw new CustomNotFoundException("Flight Not found for "+flightId);
        }
        return flightMapper.flightToFlightDTO(optionalFlight.get());
    }

    @Override
    public FlightDTO createNewFlight(FlightDTO flightDTO){
        Flight savedFlight=flightRepository.save(flightMapper.flightDTOtoFlight(flightDTO));
        return flightMapper.flightToFlightDTO(savedFlight);
    }

    @Override
    public FlightDTO updateExistsFlight(Long flightId, FlightDTO flightDTO)  {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            log.error("Flight Not found : "+flightId);
            throw new CustomNotFoundException("Current flight not exists"+flightId);
        }
        Flight flight = optionalFlight.get();
        flightMapper.updateFlightFromDTO(flightDTO,flight);
        return flightMapper.flightToFlightDTO(flightRepository.save(flight));
    }
}
