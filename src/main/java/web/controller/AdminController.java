package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "admin")
    public String adminAllUsers(Model model) {
        model.addAttribute("admin", userService.allUsers());
        return "admin";
    }

    @GetMapping(value = "user")
    public String userAllUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUserName(principal.getName()));

        return "user";
    }

    @GetMapping("add")
    public String getUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.allRoles());
        return "add";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute("addUser") User user,
                          @RequestParam(value = "newRole") String[] role) {
        user.setRoles(getAddRole(role));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("edit/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edit", userService.getUserById(id));
        model.addAttribute("role", roleService.allRoles());
        return "edit";
    }

    @PostMapping("edit/{id}")
    public String editUser(@ModelAttribute("edit") User user,
                           @RequestParam(value = "newRole") String[] role) {
        user.setRoles(getAddRole(role));
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

    private Set<Role> getAddRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            roleSet.add(roleService.findByRoleName(role[i]));
        }
        return roleSet;
    }
}