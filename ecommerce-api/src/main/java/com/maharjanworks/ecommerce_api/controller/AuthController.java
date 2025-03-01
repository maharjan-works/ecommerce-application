package com.maharjanworks.ecommerce_api.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.maharjanworks.ecommerce_api.auth.service.AuthService;
import com.maharjanworks.ecommerce_api.auth.utils.JwtUtils;
import com.maharjanworks.ecommerce_api.dto.AuthRequest;
import com.maharjanworks.ecommerce_api.dto.AuthResponse;
import com.maharjanworks.ecommerce_api.dto.RegisterRequest;
import com.maharjanworks.ecommerce_api.dto.UserDto;
import com.maharjanworks.ecommerce_api.model.User;
import com.maharjanworks.ecommerce_api.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.username(), authRequest.password()));

        }catch (BadCredentialsException ex){
            throw  new BadCredentialsException("Invalid Username or Password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        AuthResponse response = new AuthResponse(
                optionalUser.get().getUsername(),
                optionalUser.get().getRole().name(),
                jwt
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }


    @PostMapping("/register/admin")
    public ResponseEntity<UserDto> registerAdmin(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(authService.registerAdminAccount(request), HttpStatus.CREATED);
    }





}
