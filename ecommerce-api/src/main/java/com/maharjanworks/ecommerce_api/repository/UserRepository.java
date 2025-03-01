package com.maharjanworks.ecommerce_api.repository;

import com.maharjanworks.ecommerce_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
