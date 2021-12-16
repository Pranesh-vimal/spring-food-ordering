package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	List<User> findAll();

	void delete(User user);

	void update(User user);

	User findById(int id);

	void init();
}