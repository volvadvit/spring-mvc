package com.volvadvit.talkie.controller;

import com.volvadvit.talkie.domain.Role;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveEditUser(
            @RequestParam("userId") User user,
            @RequestParam("username") String username,
            @RequestParam Map<String,String> form
    ) {
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
        return "redirect:/user";
    }
}
