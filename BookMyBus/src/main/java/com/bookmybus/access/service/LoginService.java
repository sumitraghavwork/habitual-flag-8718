package com.bookmybus.access.service;

import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.AdminDto;
import com.bookmybus.access.models.UserDto;

public interface LoginService {
	
	public String loginAdmin(AdminDto admin) throws LoginException;
	
	public String loginUser(UserDto user) throws LoginException;
	
	public String logoutAdmin(String key) throws LoginException;

	public String logoutUser(String key) throws LoginException;
	
	
}
