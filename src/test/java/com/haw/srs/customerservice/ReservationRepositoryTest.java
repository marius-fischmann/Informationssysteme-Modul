package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class ReservationRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();
        this.reservationRepository.deleteAll();
        this.movieRepository.deleteAll();

        Movie movie1 = new Movie("James Bond 007", 120);
        Reservation reservation1 = new Reservation(movie1, 5, 19);
        Reservation reservation2 = new Reservation(movie1,5, 19);
        Reservation reservation3 = new Reservation(movie1,5, 19);
        Reservation reservation4 = new Reservation(movie1,5, 19);
        //movieRepository.save(movie1);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);
        reservations.add(reservation4);

        reservationRepository.saveAll(reservations);
    }

    @Test
    void getAllReservationsByMovieTest() {
        List<Reservation> reservations = reservationRepository.findReservationByMovie_Title("James Bond 007");
        assertThat(reservationRepository.count()).isEqualTo(4);
        assertThat(reservations).size().isEqualTo(4);
    }
}
