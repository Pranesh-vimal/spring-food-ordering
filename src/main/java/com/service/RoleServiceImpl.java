package com.service;

import java.util.List;

import com.model.Role;
import com.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void init() {
        List<Role> roles = roleRepository.findAll();

        if (roles.isEmpty()) {
            Role role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);

            role = new Role();
            role.setName("STAFF");
            roleRepository.save(role);
        }
    }
}
