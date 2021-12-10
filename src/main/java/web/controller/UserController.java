package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


	@GetMapping(value = "admin")
	public String adminAllUsers(Model model) {
		model.addAttribute("admin", userService.allUsers());
		System.out.println(userService.allUsers());
		return "admin";
	}

	@GetMapping(value = "user")
	public String userAllUsers(Model model) {
		model.addAttribute("user", userService.allUsers());
		System.out.println(userService.allUsers());
		return "user";
	}

	@GetMapping("add")
	public String getUser() { //заполнение
		return "add";
	}

	@PostMapping("add")
	public String addUser(@ModelAttribute("addUser") User user) {
		userService.addUser(user);
		return "redirect:/admin";
	}

	@GetMapping("edit/{id}")
	public String getUserById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("edit", userService.getUserById(id));
		return "edit";
	}

	@PostMapping("edit/{id}")
	public String editUser(@ModelAttribute("edit") User user) {
		userService.editUser(user);
		return "redirect:/admin";
	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		userService.deleteUser(user);
		return "redirect:/admin";
	}

}