package com.bookmybus.access.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.models.AdminDto;
import com.bookmybus.access.models.UserDto;
import com.bookmybus.access.service.LoginService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("loginController")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/adminLogin")
	public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody AdminDto admin) throws LoginException {

		String res = loginService.loginAdmin(admin);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	@PostMapping("/adminLogout")
	public ResponseEntity<String> logoutAdminHandler(@RequestParam("key") String key) throws LoginException {

		String res = loginService.logoutAdmin(key);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}
	
	@PostMapping("/userLogin")
	public ResponseEntity<String> loginUserHandler(@Valid @RequestBody UserDto user) throws LoginException {

		String res = loginService.loginUser(user);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	@PostMapping("/userLogout")
	public ResponseEntity<String> logoutUserHandler(@RequestParam("key") String key) throws LoginException {

		String res = loginService.logoutUser(key);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}
	
}
