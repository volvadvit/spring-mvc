package com.volvadvit.talkie.controller;

import com.volvadvit.talkie.controller.utils.ControllerUtils;
import com.volvadvit.talkie.domain.Message;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.repository.MessageRepo;
import com.volvadvit.talkie.service.FileUploadService;
import com.volvadvit.talkie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired UserService userService;
    @Autowired private MessageRepo messageRepo;
    @Autowired private FileUploadService fileUploadService;

    @GetMapping("/{username}")
    public String getUserMessages(
            @AuthenticationPrincipal User user,
            @PathVariable String username,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        User userByRequest = userService.getByUsername(username);
        Page<Message> messages = messageRepo.findByAuthor(userByRequest, pageable);

        model.addAttribute("page", messages);
        model.addAttribute("username", username);
        model.addAttribute("subscriptionsCount", userByRequest.getSubscriptions().size());
        model.addAttribute("subscribersCount", userByRequest.getSubscribers().size());
        model.addAttribute("url", "/messages/" + username);

        if (Objects.equals(user.getUsername(), username)) {
            model.addAttribute("isSubscriber", false);
            model.addAttribute("isCurrentUser", true);
            return "userMessages";
        } else if (userByRequest.getId() != -1) {
            boolean isSubscriber = user.getSubscriptions().contains(userByRequest);
            model.addAttribute("isSubscriber", isSubscriber);
            model.addAttribute("isCurrentUser", false);
            return "userMessages";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping()
    public String editUserMessage(
            @AuthenticationPrincipal User user,
            @RequestParam("message") Long messageId,
                                  Model model) {
        Message message = messageRepo.findById(messageId).orElseThrow(
                () -> new IllegalArgumentException("Message not found"));
        model.addAttribute("message", message);
        model.addAttribute("username", user.getUsername());
        return "messageEdit";
    }

    @PostMapping()
    public String saveEditUserMessage(@AuthenticationPrincipal User user,
                                      @Valid Message message,
                                      BindingResult bindingResult,
                                      Model model,
                                      @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getErrorsMap(bindingResult);
            model.addAllAttributes(errorMap);
            model.addAttribute("message", message);
        } else {
            // add file to message
            fileUploadService.uploadFile(message, file);
            messageRepo.save(message);
        }
        model.addAttribute("username", user.getUsername());
        return "messageEdit";
    }
}
