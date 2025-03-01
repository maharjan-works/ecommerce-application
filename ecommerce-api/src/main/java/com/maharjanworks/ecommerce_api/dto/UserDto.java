package com.maharjanworks.ecommerce_api.dto;

import com.maharjanworks.ecommerce_api.enums.Role;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String username,
        String role
) { }
