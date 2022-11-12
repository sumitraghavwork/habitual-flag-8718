package com.bookmybus.access.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.AdminRepo;
import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.dao.UserRepo;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.AdminDto;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.access.models.UserDto;
import com.bookmybus.models.Admin;
import com.bookmybus.models.User;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CurrentUserSessionRepo currUserSession;

	@Override
	public String loginAdmin(AdminDto admin) throws LoginException {

		Admin existingUser = adminRepo.findByAdminUsername(admin.getAdminUsername());

		if (existingUser == null)
			throw new LoginException("Invalid credentials. Username does not exist " + admin.getAdminUsername());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getAdminId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingUser.getAdminPassword().equals(admin.getAdminPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = true;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getAdminId(), key, isAdmin,
					LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");

	}

	@Override
	public String loginUser(UserDto user) throws LoginException {

		User existingUser = userRepo.findByUserName(user.getUserName());

		if (existingUser == null)
			throw new LoginException("Invalid credentials. Username does not exist " + user.getUserName());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getUserLoginId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingUser.getPassword().equals(user.getPassword())) {

			String key = RandomString.make(6);
			
			Boolean isAdmin = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserLoginId(), key, isAdmin,
					LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {

		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

	@Override
	public String logoutUser(String key) throws LoginException {

		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

}
