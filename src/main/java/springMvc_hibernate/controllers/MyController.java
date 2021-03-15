package springMvc_hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springMvc_hibernate.dao.UserDao;
import springMvc_hibernate.model.Role;
import springMvc_hibernate.model.User;
import springMvc_hibernate.service.RoleService;
import springMvc_hibernate.service.UserService;


import java.util.*;

@Controller
@RequestMapping("")
public class MyController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "users";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userPage";
    }


    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.show(id));
        return "idUsers";
    }

    @GetMapping(value = "/admin/new")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("addUser", new User());
        modelMap.addAttribute("allRoles", roleService.getAllRoles());
        return "userAdd";
    }

    @Transactional
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addUserBd(@ModelAttribute("addUser") User user,
                            @RequestParam(value = "select_role", required = false) String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(roleService.getAllRoles().get(0));
            } else if (s.equals("ROLE_USER")) {
                rol.add(roleService.getAllRoles().get(1));
            }
        }

        user.setRoles(rol);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.show(id));
        modelMap.addAttribute("allRoles", roleService.getAllRoles());
        return "edit";
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "select_roles", required = false) String[] role) {

        userService.ubdate(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
