package com.service.Nimbus.DTO;

public record UpdatePassword(
        String username,
        String oldPassword,
        String newPassword
) {
}
