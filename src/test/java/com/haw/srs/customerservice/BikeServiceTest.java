package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class BikeServiceTest {

    @Autowired
    private BikeService bikeService;

    @Autowired
    private BikeRepository bikeRepository;

    @BeforeEach
    void setUp() {
        bikeRepository.deleteAll();
        bikeRepository.save(new Bike(1l, "not loan bike", State.INTACT, false));
        bikeRepository.save(new Bike(2l, "loan bike", State.INTACT, true));
        bikeRepository.save(new Bike(3l, "faulty bike", State.FAULTY, false));
        bikeRepository.save(new Bike(4l, "faulty bike", State.FAULTY, false));
        bikeRepository.save(new Bike(5l, "faulty bike", State.FAULTY, false));
    }

    @Transactional
    @Test
    void loanBikeTest() {
        Bike bike = bikeRepository.getOne(1l);
        assertEquals(false, bike.isOnLoan());
        try {
            bikeService.loanBike(bike);
        } catch (BikeNotFoundException e) {
            e.printStackTrace();
            assertFalse(true);
        }
        Bike bikeActual = bikeRepository.getOne(1l);
        assertEquals(true, bikeActual.isOnLoan());
    }

    @Transactional
    @Test
    void restoreBikeTest() {
        Bike bike = bikeRepository.getOne(2l);
        assertEquals(true, bike.isOnLoan());
        try {
            bikeService.restoreBike(bike);
        } catch (BikeNotFoundException e) {
            e.printStackTrace();
            assertFalse(true);
        }
        Bike actualBike = bikeRepository.getOne(2l);
        assertEquals(false, actualBike.isOnLoan());
    }

    @Transactional
    @Test
    void setBikeStateToFaultyTest() {
        Bike bike = bikeRepository.getOne(1l);
        assertEquals(State.INTACT, bike.getState());
        try {
            bikeService.setBikeStateToFaulty(bike);
        } catch (BikeNotFoundException e) {
            e.printStackTrace();
            assertFalse(true);
        }
        Bike bikeActual = bikeRepository.getOne(1l);
        assertEquals(State.FAULTY, bikeActual.getState());
    }

    @Transactional
    @Test
    void setBikeStateToRepairedTest() {
        Bike bike = bikeRepository.getOne(3l);
        assertEquals(State.FAULTY, bike.getState());
        try {
            bikeService.setBikeStateToRepaired(bike);
        } catch (BikeNotFoundException e) {
            e.printStackTrace();
            assertFalse(true);
        }
        Bike bikeActual = bikeRepository.getOne(3l);
        assertEquals(State.REPAIRED, bikeActual.getState());
    }

    @Transactional
    @Test
    void addBikeToRepositoryTest() {
        Bike newBike = new Bike(111l, "new test bike", State.INTACT, false);
        bikeService.addBikeToRepository(newBike);
        Bike newAddedBike = bikeRepository.getOne(111l);
        assertEquals(newBike.getId(), newAddedBike.getId());
    }

    @Transactional
    @Test
    void removeBikeToRepositoryTest() {
        Bike bike = bikeRepository.getOne(1l);
        assertEquals(true, bikeRepository.existsById(bike.getId()));
        bikeService.removeBikeToRepository(bike);
        assertEquals(false, bikeRepository.existsById(bike.getId()));
    }


    @Transactional
    @Test
    void getAllBikeStatesTest() {
        Map <Long, State>  states = bikeService.getAllBikeStates();
        List<State> statesList = new ArrayList<>();
        for(Long id : states.keySet()){
            statesList.add(states.get(id));
        }
        assertEquals(State.INTACT, statesList.get(0));
        assertEquals(State.INTACT, statesList.get(1));
        assertEquals(State.FAULTY, statesList.get(2));
        assertEquals(State.FAULTY, statesList.get(3));
        assertEquals(State.FAULTY, statesList.get(4));
    }
}