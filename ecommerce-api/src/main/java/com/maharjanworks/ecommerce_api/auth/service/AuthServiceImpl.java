package com.maharjanworks.ecommerce_api.auth.service;

import com.maharjanworks.ecommerce_api.dto.RegisterRequest;
import com.maharjanworks.ecommerce_api.dto.UserDto;
import com.maharjanworks.ecommerce_api.enums.Role;
import com.maharjanworks.ecommerce_api.exception.EmailAlreadyExistsException;
import com.maharjanworks.ecommerce_api.exception.UsernameAlreadyExistsException;
import com.maharjanworks.ecommerce_api.model.User;
import com.maharjanworks.ecommerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto register(RegisterRequest request){

        Optional<User> optionalUserEmail = userRepository.findByEmail(request.email());
        if (optionalUserEmail.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists, Try Different!");
        }

        Optional<User> optionalUsername = userRepository.findByUsername(request.username());
        if(optionalUsername.isPresent()){
            throw new UsernameAlreadyExistsException("Username Already Exists, Try Different!");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.CUSTOMER);

        var savedUser = userRepository.save(user);

        UserDto dto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                savedUser.getRole().name()
        );
        return dto;
    }


    public UserDto  registerAdminAccount(RegisterRequest request){

        Optional<User> optionalAdmin = userRepository.findByEmail(request.email());
        if(optionalAdmin.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists. Try Different!");
        }
        Optional<User> optionalUsername = userRepository.findByUsername(request.username());
        if (optionalAdmin.isPresent()){
            throw new UsernameAlreadyExistsException("Username Already Exists. Try Different");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ADMIN);

        User savedUser = userRepository.save(user);

        UserDto dto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                savedUser.getRole().name()
        );
        return dto;

    }


}
