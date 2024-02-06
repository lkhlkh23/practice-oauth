package practice.oauth.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity moveGoogleLogin() {
		log.info("login click!");
		final String authUrl = googleConfig.getUrl();
		try {
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(new URI(authUrl));

			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping(value = "/login/oauth2/code/google")
	public GoogleLoginResponseV1 redirectGoogleLogin(@RequestParam(value = "code", required = false, defaultValue = "") final String authCode) {
		final String email = googleLoginService.getSocialEmail(authCode);
		if(!googleLoginService.isRegistered(email)) {
			return GoogleLoginResponseV1.of(email);
		}

		return GoogleLoginResponseV1.builder()
									.email(email)
									.accessToken("TODO : accessToken 생성")
									.refreshToken("TODO : refreshToken 생성")
									.isRegistered(true)
									.build();
	}
}
