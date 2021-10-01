package com.airline.service.api.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.service.api.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
