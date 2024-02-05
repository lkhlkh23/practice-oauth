package practice.oauth.controller;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import practice.oauth.config.GoogleConfig;
import practice.oauth.controller.dto.GoogleLoginRequest;
import practice.oauth.controller.dto.GoogleLoginResponseV1;
import practice.oauth.controller.dto.GoogleLoginV1;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	private final GoogleConfig googleConfig;

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
	public ResponseEntity redirectGoogleLogin(@RequestParam(value = "code", required = false, defaultValue = "") final String authCode) {
		final RestTemplate restTemplate = new RestTemplate();

		try {
			final GoogleLoginRequest requestParams = googleConfig.of(authCode);
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			final String loginUrl = googleConfig.getAuthUrl() + "/token";
			final ResponseEntity<GoogleLoginResponseV1> loginResponse = restTemplate.postForEntity(loginUrl, new HttpEntity<>(requestParams, headers), GoogleLoginResponseV1.class);
			final GoogleLoginResponseV1 googleLoginResponse = loginResponse.getBody();

			final String jwtToken = googleLoginResponse.getIdToken();
			final String requestUrl = UriComponentsBuilder.fromHttpUrl(googleConfig.getAuthUrl() + "/tokeninfo").queryParam("id_token", jwtToken).toUriString();
			final GoogleLoginV1 userInfoDto = restTemplate.getForObject(requestUrl, GoogleLoginV1.class);
			return ResponseEntity.ok().body(userInfoDto);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().build();
	}
}
