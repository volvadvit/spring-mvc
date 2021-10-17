package com.volvadvit.talkie.controller;

import com.volvadvit.talkie.controller.utils.ControllerUtils;
import com.volvadvit.talkie.domain.Message;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.domain.dto.MessageDTO;
import com.volvadvit.talkie.repository.MessageRepo;
import com.volvadvit.talkie.service.FileUploadService;
import com.volvadvit.talkie.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainPageController {

    @Autowired private MessageRepo messageRepo;
    @Autowired private MessageService messageService;
    @Autowired private FileUploadService fileUploadService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String getMainMessagePage(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MessageDTO> page = messageService.getPageableMessageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getErrorsMap(bindingResult);
            model.addAllAttributes(errorMap);
            model.addAttribute("message", message);
        } else {
            // add file to message
            fileUploadService.uploadFile(message, file);
            model.addAttribute("message", null);
            messageRepo.save(message);
        }

        // update message list
        Page<MessageDTO> page = messageService.getPageableMessageList(pageable, null, user);
        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        return "main";
    }
}