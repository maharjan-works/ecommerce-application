package com.maharjanworks.ecommerce_api.dto;

public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String username,
    String password
) { }
