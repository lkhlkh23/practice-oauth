package practice.oauth.config;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import practice.oauth.controller.dto.GoogleLoginRequest;

@Component
@Getter
public class GoogleConfig {

	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String clientSecret;

	@Value("${spring.security.oauth2.client.registration.google.redirect-url}")
	private String redirectUrl;

	@Value("${spring.security.oauth2.client.registration.google.scope}")
	private String scope;

	@Value("${spring.security.oauth2.client.registration.google.login-url}")
	private String loginUrl;

	@Value("${spring.security.oauth2.client.registration.google.auth-url}")
	private String authUrl;

	public String getUrl() {
		final Map<String, Object> parameters = new HashMap<>() {{
			put("client_id", clientId);
			put("redirect_uri", redirectUrl);
			put("response_type", "code");
			put("scope", scope.replaceAll(",", "%20"));
		}};

		final String query = parameters.entrySet()
									   .stream()
									   .map(param -> param.getKey() + "=" + param.getValue())
								       .collect(Collectors.joining("&"));

		return String.format("%s/o/oauth2/v2/auth?%s", loginUrl, query);
	}

	public GoogleLoginRequest of(final String authCode) {
		return GoogleLoginRequest.builder()
							     .clientId(clientId)
							     .clientSecret(clientSecret)
							     .code(authCode)
							     .redirectUri(redirectUrl)
							     .grantType("authorization_code")
							     .build();
	}

}
