package com.example.zellowapplication.controller;

import com.example.zellowapplication.entity.User;
import com.example.zellowapplication.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import com.example.zellowapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Objects;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");

            return "/register";
        }
        System.out.println(userDto);

        boolean existingName = userService.findAllUsers()
                .stream()
                .anyMatch(user -> Objects.equals(user.getFirstName(), userDto.getFirstName()) || Objects.equals(user.getLastName(), userDto.getLastName()));

        if (existingName) {
            result.rejectValue("firstName", null,
                    "This name already exist");

            return "/register";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

    // handler method to handle list of users
    @GetMapping("/user")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

//     handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}