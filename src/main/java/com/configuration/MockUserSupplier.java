package com.configuration;

import com.configuration.model.CustomUser;
import com.entity.Role;
import com.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MockUserSupplier {
    private final String[] usernames = {"user", "admin", "DariusNicolae"};
    private final String[][] roles = {{"ROLE_User"}, {"ROLE_Admin"}, {"ROLE_Admin"}};
    private final String password = "1234";
    public final static MockUserSupplier supplier = new MockUserSupplier();

    private MockUserSupplier() {
    }

    private Integer userCount = 0;

    Supplier<Set<Role>> rolesSupplier = () -> {
        Set<Role> allRoles = Arrays.asList(roles)
                .stream()
                .flatMap(roles -> Arrays.stream(roles).map(role-> new Role(null, role))
                        .collect(Collectors.toList())
                        .stream())
                .collect(Collectors.toSet());
        return allRoles;
    };
    Function<Role, User> userSupplier = (Role roleToAdd) -> {
        User customUser = new User();
        if (userCount < 3) {
            String username = usernames[userCount];
            String[] userRoles = roles[userCount];
            String userPassword = password;
            customUser.setUsername(username);
            customUser.setRoles(Arrays.asList(roleToAdd));
            customUser.setPassword(userPassword);
            userCount++;
        } else {
            customUser.setUsername("Duplicate" + Math.abs(new Random().nextLong()));
            customUser.setPassword(password);
            customUser.setRoles(Arrays.asList(roleToAdd));
        }
        return customUser;
    };
}
