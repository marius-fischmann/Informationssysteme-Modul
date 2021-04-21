package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
class BikeNotFoundException extends Exception {

    private final Long bikeId;
    private final String bikeDescription;

    BikeNotFoundException(Long bikeId) {
        super(String.format("Could not find bike with number %d.", bikeId));

        this.bikeId = bikeId;
        this.bikeDescription = "";
    }

    BikeNotFoundException(String bikeDescription) {
        super(String.format("Could not find customer with lastname %s.", bikeDescription));

        this.bikeId = 0L;
        this.bikeDescription = bikeDescription;
    }
}