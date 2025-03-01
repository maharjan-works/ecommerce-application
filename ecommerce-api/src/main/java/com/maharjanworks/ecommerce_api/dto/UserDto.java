package com.maharjanworks.ecommerce_api.dto;

import jakarta.validation.constraints.NotBlank;
public class UserDto {


    @NotBlank(message="Please provide First Name!")
    private String firstName;

    @NotBlank(message="Please provide Last Name!")
    private String lastName;

    @NotBlank(message="Please provide Email!")
    private String email;

    @NotBlank(message="Please provide Username!")
    private String username;

    @NotBlank(message="Please provide Password!")
    private String password;

    @NotBlank(message="Please provide Role!")
    private String role;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
