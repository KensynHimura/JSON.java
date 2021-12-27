package web.controller;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import web.exception.NoSuchUserException;
import web.model.User;
import web.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public AdminController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/login")          //авторизация
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping("/users")
    public List<User> adminAllUsers() {                     // выводит всех User's
        List<User> allUsers = userService.allUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {         //находит по id
        User user;
        try {
            user = userService.getUserById(id);
        } catch (NoSuchElementException e) {
           throw new  NoSuchUserException("There is no User ID = " + id + " in Database");
        }
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {          // добавляет
        userService.addUser(user);
        return user;
    }

    @PutMapping ("/users")
    public User editUser(@RequestBody User user) {               // изменяет
        userService.addUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {           // удаляет по  id
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no User ID = " + id + " in Database");
        }
        userService.deleteUser(id);
        return "User with ID = " + id + " was delete";
    }



//    @PostMapping("/admin")
//    public User addUser(@RequestBody User user) {
//        user.setRoles(getAddRole(role));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }


//
//    @GetMapping(value = "/login")
//    public String loginPage() {
//        return "/login";
//    }
//
//
//    @GetMapping(value = "admin")
//    public String adminAllUsers(@AuthenticationPrincipal User user, Model model) {
//       model.addAttribute("admin", userService.allUsers());
//        model.addAttribute("role", userService.allRoles());
//        model.addAttribute("user", user);
//        return "/admin";
//    }


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
//    private Set<Role> getAddRole(String[] role) {
//        Set<Role> roleSet = new HashSet<>();
//        for (int i = 0; i < role.length; i++) {
//            roleSet.add(userService.findByRoleName(role[i]));
//        }
//        return roleSet;
//    }


}