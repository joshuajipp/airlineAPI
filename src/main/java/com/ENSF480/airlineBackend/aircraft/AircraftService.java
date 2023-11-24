package com.ENSF480.airlineBackend.aircraft;

import java.util.Optional;
import java.util.List;

public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Optional<Aircraft> findById(Long id) {
        return aircraftRepository.findById(id);
    }

    public List<Aircraft> getAircrafts() {
        return aircraftRepository.findAll();
        
    }

    public void createNewAircraft(Aircraft aircraft) {
        Optional<Aircraft> aircraftOptional = aircraftRepository.findAircraftByName(aircraft.getName());
        
        if(aircraftOptional.isPresent()) {
            throw new IllegalStateException("aircraft name taken");
        }

        aircraftRepository.save(aircraft);
    }

    public void deleteAircraft(Long aircraftId) {
        boolean exists = aircraftRepository.existsById(aircraftId);

        if(!exists) {
            throw new IllegalStateException("aircraft with id " + aircraftId + " does not exists");
        }

        aircraftRepository.deleteById(aircraftId);
    }



}
