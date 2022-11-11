package com.bookmybus.access.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmybus.access.dao.AdminRepo;
import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.models.Admin;

@Repository
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Admin saveUser(Admin user) throws AdminException {

		Admin existingUserName = adminRepo.findByAdminUsername(user.getAdminUsername());

		if (existingUserName != null)
			throw new AdminException("Username already exists " + user.getAdminUsername());

		return adminRepo.save(user);
	}

	@Override
	public Admin updateUser(Admin user, String key) throws AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update a customer");
		}

		if (user.getAdminId() == loggedInUser.getUserId()) {
			return adminRepo.save(user);
		} else
			throw new AdminException("Invalid Admin Details, please login first");

	}

	@Override
	public Admin deleteUser(String adminUsername) throws AdminException {

		Admin existingUser = adminRepo.findByAdminUsername(adminUsername);

		if (existingUser == null)
			throw new AdminException("Admin does not exists with this username " + adminUsername);

		adminRepo.delete(existingUser);

		return existingUser;

	}

	@Override
	public Admin findByAdminId(Integer adminId) throws AdminException {

		Optional<Admin> existingUser = adminRepo.findById(adminId);

		if (existingUser.isPresent())
			return existingUser.get();
		else
			throw new AdminException("Admin does not exists with this adminId " + adminId);
	}

	@Override
	public Admin findByUserName(String adminUserName) throws AdminException {

		Admin existingUser = adminRepo.findByAdminUsername(adminUserName);

		if (existingUser != null)
			return existingUser;
		else
			throw new AdminException("Admin does not exists with this userName " + adminUserName);
	}

	@Override
	public List<Admin> findAllUsers() throws AdminException {
		List<Admin> users = adminRepo.findAll();

		if (users.isEmpty())
			throw new AdminException("No Admins Found");

		return users;
	}

}
