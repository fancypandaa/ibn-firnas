package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.dto.mapper.AirPlaneMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.AirPlaneRepository;
import com.ibn.firnas.repostiories.FlightRepository;
import com.ibn.firnas.service.AirPlaneService;
import com.ibn.firnas.specifications.AirPlaneSpecification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AirPlaneServiceImpl implements AirPlaneService {
    private final AirPlaneRepository airPlaneRepository;
    private final FlightRepository flightRepository;
    private final AirPlaneMapper airPlaneMapper;

    public AirPlaneServiceImpl(AirPlaneRepository airPlaneRepository, FlightRepository flightRepository, AirPlaneMapper airPlaneMapper) {
        this.airPlaneRepository = airPlaneRepository;
        this.flightRepository = flightRepository;
        this.airPlaneMapper = airPlaneMapper;
    }

    @Override
    public AirPlaneDTO findAirPlaneById(Long airPlaneId) {
        Optional<AirPlane> optionalAirPlane= airPlaneRepository.findById(airPlaneId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomNotFoundException("Airplane Not Found"+airPlaneId);
        }
        return airPlaneMapper.airPlaneToAirPlaneDTO(optionalAirPlane.get());
    }

    @Override
    public AirPlaneDTO addNewAirPlane(AirPlaneDTO airPlaneDTO) {
        AirPlane airPlane= airPlaneRepository.save(airPlaneMapper.airPlaneDTOtoAirPlane(airPlaneDTO));
        return airPlaneMapper.airPlaneToAirPlaneDTO(airPlane);
    }

    @Override
    public AirPlaneDTO updateAirPlaneInfo(Long airPlaneId,AirPlaneDTO airPlaneDTO) {
        Optional<AirPlane> optionalAirPlane=airPlaneRepository.findById(airPlaneId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomNotFoundException("AirPlane with "+airPlaneId +" Not found");
        }
        AirPlane airPlane=optionalAirPlane.get();
        airPlaneMapper.updateAirPlaneFromDTO(airPlaneDTO,airPlane);
        return airPlaneMapper.airPlaneToAirPlaneDTO(airPlaneRepository.save(airPlane));
    }

    @Override
    public AirPlaneDTO assignFlightToAirPlane(Long airPlaneId, Long flightId){
        Optional<AirPlane> optionalAirPlane=airPlaneRepository.findById(airPlaneId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomNotFoundException("AirPlane with "+airPlaneId +" Not found");
        }
        Optional<Flight> optionalFlight=flightRepository.findById(flightId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomNotFoundException("Flight with "+flightId +" Not found");
        }
        AirPlane airPlane= optionalAirPlane.get();
        airPlane.addFlights(optionalFlight.get());
        AirPlaneDTO airPlaneDTO = airPlaneMapper.airPlaneToAirPlaneDTO(airPlaneRepository.save(airPlane));
        return airPlaneDTO;
    }

    @Override
    public List<AirPlaneDTO> findAllAirPlanesWithLastCheckTankBetween(Date from, Date to) {
        List<AirPlane> airPlaneList= airPlaneRepository.findAll(AirPlaneSpecification.dateBetween(from,to));
        return airPlaneMapper.airPlanesToAirPlaneDTOs(airPlaneList);
    }
}
