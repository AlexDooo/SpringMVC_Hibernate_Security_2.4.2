package springMvc_hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springMvc_hibernate.model.User;
import springMvc_hibernate.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class MyController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.show(id));
        return "idUsers";
    }

    @GetMapping("/new")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("addUser", new User());
        return "userAdd";
    }

    @PostMapping()
    public String addUserBd(@ModelAttribute("addUser") User user) {
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Integer id) {
        userService.ubdate(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/people";
    }
}
