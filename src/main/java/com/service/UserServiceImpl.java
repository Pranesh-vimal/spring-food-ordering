package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		if (user.getRole() == null) {
			Role userRole = roleRepository.findByName("STAFF");
			user.setRole(userRole);
		}

		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById((long) id).orElse(new User());
	}

	@Override
	public void update(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userRepository.save(user);
	}

	@Override
	public void init() {
		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			Role admin = roleRepository.findByName("ADMIN");
			Role staff = roleRepository.findByName("STAFF");

			User user = new User();
			user.setUsername("admin123");
			user.setPassword(bCryptPasswordEncoder.encode("admin123"));
			user.setRole(admin);
			userRepository.save(user);

			user = new User();
			user.setUsername("staff123");
			user.setPassword(bCryptPasswordEncoder.encode("staff123"));
			user.setRole(staff);
			userRepository.save(user);
		}
	}

}