package com.bookmybus.access.service;

import java.util.List;

import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.UserDto;
import com.bookmybus.models.User;

public interface UserService {
	
	public User saveUser(User user) throws UserException;

	public User updateUser(User user, String key) throws UserException, LoginException;

	public User deleteUser(String username) throws UserException;

	public User findByUserLoginId(Integer userLoginId) throws UserException;

	public User findByEmail(String email) throws UserException;

	public User findByUserName(String userName) throws UserException;
	
	public User findByUserNameOrEmail(String userName, String email) throws UserException;
	
	public List<User> findAllUsers() throws UserException;
}
