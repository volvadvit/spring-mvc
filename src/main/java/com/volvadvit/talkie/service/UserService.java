package com.volvadvit.talkie.service;

import com.volvadvit.talkie.domain.Role;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepo userRepo;
    @Autowired private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        if (!user.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Talkie. Please verify your email address by clicking the link below.\n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.sendVerifyMail(user.getEmail(), "Activation Code", message);
        }

        userRepo.save(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
