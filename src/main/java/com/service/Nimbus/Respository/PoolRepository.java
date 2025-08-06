package com.service.Nimbus.Respository;

import com.service.Nimbus.Model.Pool;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;

public interface PoolRepository extends CrudRepository<Pool, Long> {

    @Query(value = "select * from pools where from_location = :from " +
            "and to_location = :to " +
            "and departure_time = :time " +
            "and seats_available >= :seats", nativeQuery = true)
    public List<Pool> searchPool(@Param("from") String from_location,
                                 @Param("to") String to_location,
                                 @Param("time")LocalDateTime departure,
                                 @Param("seats") int seats_required);
}
