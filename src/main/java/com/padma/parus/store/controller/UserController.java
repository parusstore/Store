package com.padma.parus.store.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class UserController {

	@ApiOperation(value = "Checks if the given email is in use")
	@GetMapping("/user")
	public Map<String, String> user() {
		Map<String, String> result = new HashMap<>();
		result.put("username", "test");
		return result;
	}

	@GetMapping("/only_for_admin")

	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	
	public String onlyForAdmin() {
		return "ADMIN data: " + UUID.randomUUID().toString();
	}
}
