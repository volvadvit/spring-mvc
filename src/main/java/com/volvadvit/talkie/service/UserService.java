package com.volvadvit.talkie.service;

import com.volvadvit.talkie.domain.Role;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepo userRepo;
    @Autowired private MailSender mailSender;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        encodeUserPassword(user);

        if (!sendVerifyEmail(user)) {
            return false;
        }

        userRepo.save(user);
        return true;
    }

    private void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private boolean sendVerifyEmail(User user) {
        if (!user.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Talkie. Please verify your email address by clicking the link below.\n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.sendVerifyMail(user.getEmail(), "Activation Code", message);
            return true;
        } else {
            return false;
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void saveEditUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        form.keySet().forEach(param -> {
            if (roles.contains(param)) {
                user.getRoles().add(Role.valueOf(param));
            }
        });

        userRepo.save(user);
    }

    public boolean updateProfile(User user, String password, String email) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

            String userEmail = user.getEmail();
            boolean isEmailChanged =  (userEmail != null && !email.equals(userEmail));

            if (isEmailChanged) {
                user.setEmail(email);
                user.setActivationCode(UUID.randomUUID().toString());
            }

            user.setPassword(password);
            encodeUserPassword(user);

            userRepo.save(user);

            if (isEmailChanged) {
                sendVerifyEmail(user);
            }
            return true;
        } else {
            return false;
        }
    }

    public User getByUsername(String username) {
        User byUsername = userRepo.findByUsername(username);
        if (byUsername != null) {
            return byUsername;
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
