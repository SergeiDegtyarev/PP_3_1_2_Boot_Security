package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping (value = "/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUser(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "/admin";

    }

    @GetMapping("/new")
    public String newUser2(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }
    @PostMapping("admin/new")
    public String creatUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "/update";
    }

    @PatchMapping("/update/{id}")
    public String patchUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
