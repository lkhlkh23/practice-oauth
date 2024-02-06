package practice.oauth.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import practice.oauth.remote.dto.GoogleLoginAuthorizationRequest;
import practice.oauth.remote.dto.GoogleLoginAuthorizationV1;
import practice.oauth.remote.dto.GoogleLoginV1;

@FeignClient(name = "google", url = "${spring.security.oauth2.client.registration.google.auth-url}")
public interface GoogleClient {

	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	GoogleLoginAuthorizationV1 getLoginAuthorization(@RequestBody final GoogleLoginAuthorizationRequest request);

	@GetMapping(value = "/tokeninfo")
	GoogleLoginV1 getLoginToken(@RequestParam(name = "id_token") final String token);

}
