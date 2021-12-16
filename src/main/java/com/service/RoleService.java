package com.service;

import java.util.List;

import com.model.Role;

public interface RoleService {
    void save(Role role);

    Role findByName(String name);

    List<Role> findAll();

    void init();
}
