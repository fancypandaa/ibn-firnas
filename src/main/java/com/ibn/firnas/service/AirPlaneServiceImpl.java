package com.ibn.firnas.service;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.dto.mapper.AirPlaneMapper;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.repostiories.AirPlaneRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.*;
import java.util.Optional;
@Service
public class AirPlaneServiceImpl implements AirPlaneService{
    private final AirPlaneRepository airPlaneRepository;
    private final AirPlaneMapper airPlaneMapper;

    public AirPlaneServiceImpl(AirPlaneRepository airPlaneRepository, AirPlaneMapper airPlaneMapper) {
        this.airPlaneRepository = airPlaneRepository;
        this.airPlaneMapper = airPlaneMapper;
    }

    @Override
    public AirPlaneDTO findAirPlaneById(Long airPlaneId) throws CustomException {
        Optional<AirPlane> optionalAirPlane= airPlaneRepository.findById(airPlaneId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomException("Airplane Not Found"+airPlaneId);
        }
        return airPlaneMapper.airPlaneToAirPlaneDTO(optionalAirPlane.get());
    }

    @Override
    public AirPlaneDTO addNewAirPlane(AirPlaneDTO airPlaneDTO) throws CustomException{
        if(Stream.of(airPlaneDTO.airline(),airPlaneDTO.planeName(),airPlaneDTO.companyName()).allMatch(Objects::isNull)){
            throw new CustomException("These Values Shouldn't be null > [airline, planeName and companyName]");
        }
        AirPlane airPlane= airPlaneRepository.save(airPlaneMapper.airPlaneDTOtoAirPlane(airPlaneDTO));
        return airPlaneMapper.airPlaneToAirPlaneDTO(airPlane);
    }

    @Override
    public AirPlaneDTO updateAirPlaneInfo(Long airPlaneId,AirPlaneDTO airPlaneDTO) throws CustomException {
        Optional<AirPlane> optionalAirPlane=airPlaneRepository.findById(airPlaneId);
        if(!optionalAirPlane.isPresent()){
            throw new CustomException("AirPlane with "+airPlaneId +" Not found");
        }
        AirPlane airPlane=optionalAirPlane.get();
       return airPlaneMapper.airPlaneToAirPlaneDTO(airPlaneRepository.save(airPlane));
    }

    @Override
    public AirPlaneDTO assignFlightToAirPlane(Long airPlaneId, Long flightId) throws CustomException {
        return null;
    }

}
