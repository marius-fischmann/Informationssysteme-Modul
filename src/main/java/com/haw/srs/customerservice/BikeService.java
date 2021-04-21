package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BikeService {

    @Autowired
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    /**
     * To loan a bike.
     *
     * @param bike
     * @throws BikeNotFoundException    when bike not found in database
     * @throws IllegalArgumentException when the chosen bike is faulty, already on loan or null
     */
    public void loanBike(Bike bike) throws BikeNotFoundException {
        Bike bikeFromRepo = bikeRepository
                .findById(bike.getId())
                .orElseThrow(() -> new BikeNotFoundException(bike.getId()));
        if (bikeFromRepo.getState() == State.FAULTY || bikeFromRepo.isOnLoan()) {
            throw new IllegalArgumentException("Bike is faulty or already on loan.");
        }
        bikeFromRepo.setOnLoan(true);
        bikeRepository.save(bikeFromRepo);
    }


    /**
     * To restore a loaned bike. If the bike is
     *
     * @param bike
     * @throws BikeNotFoundException    when bike not found in database
     * @throws IllegalArgumentException when bike not even loaned.
     */
    public void restoreBike(Bike bike) throws BikeNotFoundException {
        Bike bikeFromRepo = bikeRepository
                .findById(bike.getId())
                .orElseThrow(() -> new BikeNotFoundException(bike.getId()));
        if (bikeFromRepo.isOnLoan()) {
            bikeFromRepo.setOnLoan(false);
            bikeRepository.save(bikeFromRepo);
        } else
            throw new IllegalArgumentException("Bike is already on loan.");
    }

    /**
     * To set the state of a bike to faulty.
     *
     * @param bike
     * @throws BikeNotFoundException    when bike not found in database
     * @throws IllegalArgumentException when bike state is already faulty.
     */
    public void setBikeStateToFaulty(Bike bike) throws BikeNotFoundException {
        Bike bikeFromRepo = bikeRepository
                .findById(bike.getId())
                .orElseThrow(() -> new BikeNotFoundException(bike.getId()));
        if (bikeFromRepo.getState() != State.FAULTY) {
            bikeFromRepo.setState(State.FAULTY);
            bikeRepository.save(bikeFromRepo);
        } else
            throw new IllegalArgumentException("Bike state is already faulty.");
    }

    /**
     * To set the state of a bike to repaired.
     *
     * @param bike
     * @throws BikeNotFoundException    when bike not found in database
     * @throws IllegalArgumentException when bike state is already intact or repaired.
     */
    public void setBikeStateToRepaired(Bike bike) throws BikeNotFoundException {
        Bike bikeFromRepo = bikeRepository
                .findById(bike.getId())
                .orElseThrow(() -> new BikeNotFoundException(bike.getId()));
        if (bikeFromRepo.getState() == State.FAULTY) {
            bikeFromRepo.setState(State.REPAIRED);
            bikeRepository.save(bikeFromRepo);
        } else
            throw new IllegalArgumentException("Bike state is already intact or repaired.");
    }

    /**
     * To add a bike entity to the repository.
     *
     * @param bike
     * @throws IllegalArgumentException when the id of the given bike already exists in repository or argument is null.
     */
    public void addBikeToRepository(Bike bike) {
        if (bike == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        if (bikeRepository.existsById(bike.getId())) {
            throw new IllegalArgumentException("Given bike has an id which already exists.");
        }
        bikeRepository.save(bike);
    }

    /**
     * To remove a bike entity from the repository.
     *
     * @param bike
     * @throws IllegalArgumentException when the id of the given bike not exists in repository or argument is null.
     */
    public void removeBikeToRepository(Bike bike) {
        if (bike == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        if (bikeRepository.existsById(bike.getId())) {
            bikeRepository.delete(bike);
        }
        else{
            throw new IllegalArgumentException("Given bike has an id which not exists.");
    }
    }


    /**
     * To get all bike states with bike id as map.
     * @return bikeStates
     */
    public Map<Long, State> getAllBikeStates() {
        Map<Long, State> bikeStates = new HashMap<>();
        List<Bike> bikesFromRepo = bikeRepository.findAll();
        for(Bike bike : bikesFromRepo){
            bikeStates.put(bike.getId(), bike.getState());
        }
        return bikeStates;
    }
}
