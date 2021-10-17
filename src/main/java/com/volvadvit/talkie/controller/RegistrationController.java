package com.volvadvit.talkie.controller;

import com.volvadvit.talkie.controller.utils.ControllerUtils;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.domain.dto.CaptchaResponseDTO;
import com.volvadvit.talkie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired private UserService userService;
    @Autowired private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    private final static String GOOGLE_CAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        String url = String.format(GOOGLE_CAPTCHA_VERIFY_URL, recaptchaSecret, captchaResponse);
        CaptchaResponseDTO responseFromCaptcha = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);

        if (responseFromCaptcha != null && !responseFromCaptcha.isSuccess()) {
            model.addAttribute("captchaError", "Captcha required!");
        }

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("password2Error", "Passwords are different!");
        }

        if (bindingResult.hasErrors() || (responseFromCaptcha != null && !responseFromCaptcha.isSuccess())) {
            Map<String, String> errorMap = ControllerUtils.getErrorsMap(bindingResult);
            model.addAllAttributes(errorMap);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        model.addAttribute("message", "Please, verify your account. Link has been sent to your email");
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activateAccount(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found for this user");
        }

        return "login";
    }
}
