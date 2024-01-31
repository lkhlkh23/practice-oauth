package practice.oauth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import practice.oauth.filter.LoginUser;
import practice.oauth.model.User;

@Controller
@RequestMapping("/view")
@Slf4j
public class HomeController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/home")
	public String home(final Model model, @LoginUser final User loginUser) {
		model.addAttribute("user", loginUser);

		return "home";
	}

}
