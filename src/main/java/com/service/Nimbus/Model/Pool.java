package com.service.Nimbus.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("pools")
public record Pool(
        @Id Long id,
        String from_location,
        String to_location,
        LocalDateTime departure_time,
        int capacity,
        int seats_available
) {
}
