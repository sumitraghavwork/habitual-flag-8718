package com.bookmybus.access.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByUserName(String userName);
	
	public User findByUserNameOrEmail(String username,String email);
	
}
