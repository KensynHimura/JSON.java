package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "admin")
    public String adminAllUsers(Model model, Principal principal ) {
       model.addAttribute("admin", userService.allUsers());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("users", new User());
        model.addAttribute("role", userService.allRoles());
        return "admin";
    }

    @PostMapping("admin")
    public String addAdmin(@ModelAttribute("addUser") User user,
                          @RequestParam(value = "newRole") String[] role) {
        user.setRoles(getAddRole(role));
        userService.addUser(user);
        return "admin";
    }



    @GetMapping(value = "user")
    public String userAllUsers(Model model, Principal principal) {
      model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user";
    }

    @GetMapping("add")
    public String getUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", userService.allRoles());
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
        model.addAttribute("role", userService.allRoles());
        return "edit";
    }

    @PostMapping("edit/{id}")
    public String editUser(@ModelAttribute("edit") User user,
                           @RequestParam(value = "newRole") String[] role) {
        user.setRoles(getAddRole(role));
        userService.addUser(user);
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
            roleSet.add(userService.findByRoleName(role[i]));
        }
        return roleSet;
    }
}