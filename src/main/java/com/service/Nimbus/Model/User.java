package com.service.Nimbus.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("users")
public record User(
    @Id String id,
    String username,
    String fullName,
    String email,
    String password,
    String userRole,
    LocalDateTime createdAt
) {
}
