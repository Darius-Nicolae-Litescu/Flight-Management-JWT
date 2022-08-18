package com.service;

import com.entity.Role;
import com.entity.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> addRoles(Set<Role> roles) {
        return new HashSet<>(this.roleRepository.saveAll(roles));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findById(username);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return this.roleRepository.getRoleByRoleName(roleName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Long roleCount() {
        return roleRepository.count();
    }

    @Override
    public boolean insertUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser != null;
    }

    @Override
    public Long userCount() {
        return userRepository.count();
    }

    @Override
    public void insertAll(List<User> usersToInsert) {
        userRepository.saveAll(usersToInsert);
    }
}
