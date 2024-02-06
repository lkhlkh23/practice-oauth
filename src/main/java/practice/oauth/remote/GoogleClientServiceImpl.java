package practice.oauth.remote;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import practice.oauth.config.GoogleConfig;
import practice.oauth.remote.dto.GoogleLoginV1;

@Service
@RequiredArgsConstructor
public class GoogleClientServiceImpl implements GoogleClientService {

	private final GoogleConfig config;
	private final GoogleClient client;

	@Override
	public String getIdToken(final String authCode) {
		return client.getLoginAuthorization(config.of(authCode)).getIdToken();
	}

	@Override
	public String getVerifiedEmail(final String token) {
		final GoogleLoginV1 response = client.getLoginToken(token);
		return "true".equals(response.getEmailVerified()) ? response.getEmail() : "";
	}

}
