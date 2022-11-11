package com.bookmybus.access.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.dao.UserRepo;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public User saveUser(User user) throws UserException {

		User existingUserName = uRepo.findByUserName(user.getUserName());
		User existingUserEmail = uRepo.findByEmail(user.getEmail());

		if (existingUserName != null)
			throw new UserException("Username already exists " + user.getUserName());
		
		if (existingUserEmail != null)
			throw new UserException("User email exists " + user.getEmail());

		return uRepo.save(user);
	}

	@Override
	public User updateUser(User user, String key) throws UserException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to update a customer");
		}

		if (user.getUserLoginId() == loggedInUser.getUserId()) {
			return uRepo.save(user);
		} else
			throw new LoginException("Invalid User Details, please login first");

	}

	@Override
	public User deleteUser(String username) throws UserException {

		User existingUser = uRepo.findByUserName(username);

		if (existingUser == null)
			throw new UserException("User does not exists with this username " + username);

		uRepo.delete(existingUser);

		return existingUser;
	}

	@Override
	public User findByUserLoginId(Integer userLoginId) throws UserException {

		Optional<User> existingUser = uRepo.findById(userLoginId);

		if (existingUser.isPresent())
			return existingUser.get();
		else
			throw new UserException("User does not exists with this userLoginId " + userLoginId);

	}

	@Override
	public User findByEmail(String email) throws UserException {

		User existingUser = uRepo.findByEmail(email);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this email " + email);

	}

	@Override
	public User findByUserName(String userName) throws UserException {
		User existingUser = uRepo.findByUserName(userName);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this userName " + userName);
	}

	@Override
	public List<User> findAllUsers() throws UserException {

		List<User> users = uRepo.findAll();

		if (users.isEmpty())
			throw new UserException("No Users Found");

		return users;
	}

	@Override
	public User findByUserNameOrEmail(String userName, String email) throws UserException {

		User existingUser = uRepo.findByUserNameOrEmail(userName, email);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this userName or email " + userName + ", " + email);
	}
}
