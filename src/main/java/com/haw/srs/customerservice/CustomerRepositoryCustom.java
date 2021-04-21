package com.haw.srs.customerservice;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findCustomersWithoutReservations();
}
