package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AllController {

    private final UserServiceImpl userService;

    public AllController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping(value = "login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value = "admin")
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
//       model.addAttribute("admin", userService.allUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.allRoles());
        return "admin";
    }


//    @GetMapping(value = "user")
//    public String userAllUsers(Model model, Principal principal) {
//      model.addAttribute("user", userDetailsService.loadUserByUsername(principal.getName()));
//        return "user";
//    }

//    @GetMapping("add")
//    public String getUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("role", userService.allRoles());
//        return "admin";
//    }
//
//    @PostMapping("add")
//    public String addUser(@ModelAttribute("addUser") User user,
//                          @RequestParam(value = "roleSet") String[] role) {
//        user.setRoles(getAddRole(role));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("edit/{id}")
//    public String getUserById(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        model.addAttribute("role", userService.allRoles());
//        model.addAttribute("edit", userService.getUserById(id));
//        return "admin";
//    }
//
//    @PostMapping("edit/{id}")
//    public String editUser(@ModelAttribute("user") User user,
//                           @RequestParam(value = "roleSet") String[] role) {
//        user.setRoles(getAddRole(role));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        User user = userService.getUserById(id);
//        userService.deleteUser(user);
//        return "redirect:/admin";
//    }
//
    private Set<Role> getAddRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            roleSet.add(userService.findByRoleName(role[i]));
        }
        return roleSet;
    }
}
