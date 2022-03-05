package com.tanmoy.SpringSecurityJWT.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanmoy.SpringSecurityJWT.model.User;
import com.tanmoy.SpringSecurityJWT.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/list")
	public ResponseEntity<?> getUserList() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		if (!auth.isAuthenticated()) {
			JSONObject json = new JSONObject();
			json.put("status", false);
			json.put("messaage", "Unsuccessfully");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
		}
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/registration")
	public ResponseEntity<?> submitRegistration(@RequestBody User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!auth.isAuthenticated()) {
			JSONObject json = new JSONObject();
			json.put("status", false);
			json.put("messaage", "Unsuccessfully");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
		}

		User currUser = userService.findUserByUsername(auth.getName());

		if (currUser.getRoleId() == 1) {
			user.setActive(true);
			user.setCompleteApproval(true);
		}

		boolean isSave = userService.save(user);
		return isSave ? ResponseEntity.ok(user)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
	}

	@PostMapping("/active")
	public ResponseEntity<?> getUserActiveForm(@RequestParam(name = "userId", required = true) int userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!auth.isAuthenticated()) {
			JSONObject json = new JSONObject();
			json.put("status", false);
			json.put("messaage", "Unsuccessfully");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
		}
		User activeUser = userService.findById(userId);
		activeUser.setActive(true);
		activeUser.setCompleteApproval(true);
		userService.update(activeUser);
		return ResponseEntity.ok(activeUser);
	}

	@PostMapping("/deactivate")
	public ResponseEntity<?> getUserDeactivateForm(@RequestParam(name = "userId", required = true) int userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!auth.isAuthenticated()) {
			JSONObject json = new JSONObject();
			json.put("status", false);
			json.put("messaage", "Unsuccessfully");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
		}
		User deactivateUser = userService.findById(userId);
		deactivateUser.setActive(false);
		userService.update(deactivateUser);
		return ResponseEntity.ok(deactivateUser);
	}
}