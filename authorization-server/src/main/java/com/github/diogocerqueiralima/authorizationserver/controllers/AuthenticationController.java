package com.github.diogocerqueiralima.authorizationserver.controllers;

import com.github.diogocerqueiralima.authorizationserver.dto.UserRegisterDto;
import com.github.diogocerqueiralima.authorizationserver.model.User;
import com.github.diogocerqueiralima.authorizationserver.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid UserRegisterDto dto) {

        User user = userService.create(
                dto.firstName(), dto.lastName(), dto.username(), dto.email(), dto.password(), dto.confirmPassword(),
                dto.agreement(), List.of()
        );

        logger.debug("User created: {}", user.getUsername());

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
