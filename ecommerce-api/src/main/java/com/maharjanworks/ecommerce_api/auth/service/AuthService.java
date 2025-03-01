package com.maharjanworks.ecommerce_api.auth.service;

import com.maharjanworks.ecommerce_api.dto.RegisterRequest;
import com.maharjanworks.ecommerce_api.dto.UserDto;

public interface AuthService {

    public UserDto register(RegisterRequest request);
}
