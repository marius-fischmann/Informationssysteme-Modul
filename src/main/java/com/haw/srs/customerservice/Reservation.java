package com.haw.srs.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int hallNumber;

    @Column
    private int seatNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Movie movie;

    public Reservation(Movie movie, int hallnumber, int seatNumber) {
        this.movie = movie;
        this.hallNumber = hallnumber;
        this.seatNumber = seatNumber;
    }
}
