package com.haw.srs.customerservice;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Customer> findCustomersWithoutReservations() {
        return entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.reservations IS EMPTY", Customer.class)
                .getResultList();
    }
}
