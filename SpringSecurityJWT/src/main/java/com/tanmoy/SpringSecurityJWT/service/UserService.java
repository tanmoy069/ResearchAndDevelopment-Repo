package com.tanmoy.SpringSecurityJWT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanmoy.SpringSecurityJWT.model.User;
import com.tanmoy.SpringSecurityJWT.repository.UserRepository;

@Service
public class UserService extends AbstractService<User> {
	
	private UserRepository userRepo;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public boolean save(User user) {
		try {
			userRepo.save(user);
			LOGGER.info("Successfully save username: " + user.getUsername());
			return true;
		} catch(Exception e) {
			LOGGER.info("Failed to save username: " + user.getUsername());
			return false;
		}
	}

	@Override
	public User findById(int id) {
		return userRepo.findByUserId(id);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public boolean update(User obj) {
		try {
			if(findById(obj.getUserId()) != null) {
				userRepo.save(obj);
				LOGGER.info("Successfully updated user: " + obj.getUserId());
				return true;
			}
			LOGGER.info("Unable to update user, user doesn't exists");
			return false;
		} catch(Exception e) {
			LOGGER.info("Failed to update user");
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if(findById(id) != null) {
				userRepo.deleteById(id);
				LOGGER.info("Successfully deleted user: " + id);
				return true;
			}
			LOGGER.info("Unable to delete user, user doesn't exists");
			return false;
		} catch(Exception e) {
			LOGGER.info("Failed to delete user");
			return false;
		}
	}
}
