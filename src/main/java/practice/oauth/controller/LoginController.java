package practice.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import practice.oauth.controller.dto.GeneralLoginRequestV1;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity generalLogin(@RequestBody final GeneralLoginRequestV1 request) {
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getId(),
																								  request.getPassword());
		try {
			final Authentication authentication = authenticationManager.authenticate(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

}
