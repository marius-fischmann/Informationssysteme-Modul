package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class MovieRepositoryTest {

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
        Movie movie2 = new Movie("Rosamunde Pilcher", 160);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
    }

    @Test
    void getMovieByTitleTest() {
        Optional<Movie> movie = movieRepository.findByTitle("James Bond 007");
        assertThat(movie).isPresent();
    }
}
