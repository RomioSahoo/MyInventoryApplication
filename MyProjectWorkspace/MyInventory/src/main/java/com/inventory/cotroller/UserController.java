package com.inventory.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.model.User;
import com.inventory.repo.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;

	@GetMapping("")
	public String HomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String SignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signUp";
	}

	@PostMapping("/save")
	public String registerUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		repo.save(user);
		return "registered";
	}

	@GetMapping("/list_users")
	public String viewUSerList(Model model) {

		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
	

}
