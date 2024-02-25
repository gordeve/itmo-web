package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPage extends Page {
    @GetMapping("/{id}")
    public String user(@PathVariable String id, Model model) {
        try {
            Long idVal = Long.parseLong(id);
            model.addAttribute("pageUser", getUserById(idVal));
        } catch (NumberFormatException e) {
            // No operations.
        }
        return "UserPage";
    }

    @GetMapping(value={"/", ""})
    public String noUser() {
        return "UserPage";
    }
}
