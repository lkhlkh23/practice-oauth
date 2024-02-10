package practice.oauth.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import practice.oauth.config.GoogleConfig;
import practice.oauth.controller.dto.GoogleLoginResponseV1;
import practice.oauth.service.GoogleLoginService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	private final GoogleConfig googleConfig;
	private final GoogleLoginService googleLoginService;

	@GetMapping("/login")
	public ResponseEntity moveGoogleLogin() throws URISyntaxException {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(new URI(googleConfig.getUrl()));

		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

	@GetMapping(value = "/login/oauth2/code/google")
	public ResponseEntity googleLoginCallback(@RequestParam(value = "code", required = false, defaultValue = "") final String authCode) throws
		URISyntaxException {
		final String email = googleLoginService.getSocialEmail(authCode);
		final HttpHeaders headers = new HttpHeaders();
		if(!googleLoginService.isRegistered(email)) {
			headers.setLocation(new URI("http://localhost:8081/view/signup?email=" + email));
			return new ResponseEntity<>(headers, HttpStatus.FOUND);
		}

		headers.setLocation(new URI("http://localhost:8081/view/home?email=" + email));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

}
