package com.bookmybus.access.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.models.AdminDto;
import com.bookmybus.access.service.AdminService;
import com.bookmybus.access.service.LoginService;
import com.bookmybus.access.service.UserService;
import com.bookmybus.models.Admin;
import com.bookmybus.models.Admin;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/adminController")
public class AdminController {

	@Autowired
	private AdminService userService;

	@GetMapping("/admins/{username}")
	public ResponseEntity<Admin> getUserDetailsHandler(@PathVariable String username) throws AdminException {

		Admin existingUser = userService.findByUserName(username);

		return new ResponseEntity<Admin>(existingUser, HttpStatus.OK);

	}

	@GetMapping("/admins")
	public ResponseEntity<List<Admin>> getAllUserDetailsHandler() throws AdminException {

		List<Admin> userList = userService.findAllUsers();

		return new ResponseEntity<List<Admin>>(userList, HttpStatus.OK);

	}

	@PostMapping("/admins")
	public ResponseEntity<Admin> registerUserHandler(@Valid @RequestBody Admin user) throws AdminException {

		Admin savedUser = userService.saveUser(user);

		return new ResponseEntity<Admin>(savedUser, HttpStatus.OK);

	}

	@PutMapping("/admins")
	public ResponseEntity<Admin> updateUserHandler(@Valid @RequestBody Admin user, @RequestParam("key") String key)
			throws AdminException, LoginException {

		Admin updatedUser = userService.updateUser(user, key);

		return new ResponseEntity<Admin>(updatedUser, HttpStatus.OK);

	}

	@DeleteMapping("/admins/{username}")
	public ResponseEntity<Admin> deleteUserHandler(@PathVariable("username") String username)
			throws AdminException, LoginException {

		Admin updatedUser = userService.deleteUser(username);

		return new ResponseEntity<Admin>(updatedUser, HttpStatus.OK);

	}

}
