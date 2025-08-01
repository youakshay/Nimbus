package com.service.Nimbus.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("trips")
public record Trip(
        @Id Long id,
        String from_location,
        String to_location,
        LocalDateTime departure_time,
        int seats_required,
        Long user_id
) {
}
