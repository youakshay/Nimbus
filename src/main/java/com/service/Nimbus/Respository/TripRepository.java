package com.service.Nimbus.Respository;

import com.service.Nimbus.Model.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
}
