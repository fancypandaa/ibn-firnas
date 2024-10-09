package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.FlightAllDTO;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.dto.mapper.FlightMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.FlightRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlightServiceImpl implements FlightService {
    private static Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, UserDetailsRepository userDetailsRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.userDetailsRepository = userDetailsRepository;
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
    public FlightAllDTO findFlightAndCrewById(Long flightId) {
        Optional<Flight> optionalFlight=flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            log.error("Flight Not found : "+flightId);
            throw new CustomNotFoundException("Flight Not found for "+flightId);
        }

        return flightMapper.flightToFlightAllDTO(optionalFlight.get());
    }

    @Override
    public FlightDTO createNewFlight(FlightDTO flightDTO){
        Flight savedFlight=flightRepository.save(flightMapper.flightDTOtoFlight(flightDTO));
        return flightMapper.flightToFlightDTO(savedFlight);
    }

    @Override
    public FlightDTO createNewFlightWithCrew(FlightDTO flight) {
        if(flight.userIds().size() < 1){
            throw new RuntimeException("user Id for crew is null");
        }
        Set<UserDetails> userDetailsList = userDetailsRepository.findByUserIdIn(flight.userIds());
        Flight newFlight = flightMapper.flightDTOtoFlight(flight);
        userDetailsList.stream().forEach(userDetails -> {
            newFlight.addUser(userDetails);
        });
        Flight savedFlight = flightRepository.save(newFlight);
        FlightDTO savedFlightDTO = flightMapper.flightToFlightDTO(savedFlight);
        return savedFlightDTO;
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

    @Override
    public FlightDTO assignFlightCrew(Long flightId, List<Long> userIds) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(!optionalFlight.isPresent()){
            log.error("Flight Not found : "+flightId);
            throw new CustomNotFoundException("Current flight not exists"+flightId);
        }
        Flight flight= optionalFlight.get();
        Set<UserDetails> userDetailsList = userDetailsRepository.findByUserIdIn(userIds);
        userDetailsList.stream().forEach(userDetails -> {
            flight.addUser(userDetails);
        });
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.flightToFlightDTO(savedFlight);
    }
}
