package com.service.Nimbus.DTO;


public record PoolDetailsResponse(
    String username,
    String fullName,
    String email,
    int seat_required
) {
}
