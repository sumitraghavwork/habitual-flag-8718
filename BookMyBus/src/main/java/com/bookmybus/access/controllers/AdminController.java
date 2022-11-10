package com.bookmybus.access.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.models.AdminDto;
import com.bookmybus.access.service.LoginService;
import com.bookmybus.models.Admin;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/adminController")
public class AdminController {

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

}
