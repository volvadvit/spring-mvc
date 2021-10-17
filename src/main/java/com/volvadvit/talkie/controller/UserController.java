package com.volvadvit.talkie.controller;

import com.volvadvit.talkie.domain.Role;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
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
        userService.saveEditUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                  @RequestParam String password, @RequestParam String email)
    {
        if (userService.updateProfile(user, password, email)) {
            return "redirect:/user/profile";
        } else {
            return "redirect:/registration";
        }
    }

    @GetMapping("/subscribe/{username}")
    public String subscribe(@AuthenticationPrincipal User user, @PathVariable String username) {
        userService.subscribe(user, username);
        return "redirect:/messages/" + username;
    }

    @GetMapping("/unsubscribe/{username}")
    public String unsubscribe(@AuthenticationPrincipal User user, @PathVariable String username) {
        userService.unsubscribe(user, username);
        return "redirect:/messages/" + username;
    }

    @GetMapping("/{type}/{username}/list")
    public String userSubscriptionsList(@PathVariable String type, @PathVariable String username, Model model) {
        User requestUser = userService.getByUsername(username);
        if (type.equals("subscribers")) {
            model.addAttribute("users", requestUser.getSubscribers());
        } else {
            model.addAttribute("users", requestUser.getSubscriptions());
        }
        model.addAttribute("type", type);
        model.addAttribute("username", username);
        return "subscriptions";
    }
}
