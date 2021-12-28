package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class RestCrudController {

    private final UserServiceImpl userService;

    public RestCrudController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> allUsers() {                     // выводит всех User's
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {         //находит по id
        return userService.getUserById(id);
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {          // добавляет
        userService.addUser(user);
        return user;
    }

    @PutMapping()
    public User editUser(@RequestBody User user) {               // изменяет
        userService.addUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {           // удаляет по  id
        userService.deleteUser(id);
    }

    @GetMapping("/roles")
    public List<Role> allRoles() {                     // выводит всех Role's
        return userService.allRoles();
    }

}
