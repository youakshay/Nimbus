package com.service.Nimbus.Respository;

import com.service.Nimbus.Model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TripRepository extends CrudRepository<Trip, Long> {
    @Query(value = """
    select id from trips
    where user_id = :userId
""", nativeQuery = true)
    Long findTripIdByUserId(@Param("userId") Long userId);
    void deleteById(Long id);
}
