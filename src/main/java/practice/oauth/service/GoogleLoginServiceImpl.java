package practice.oauth.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import practice.oauth.infra.UserRepository;
import practice.oauth.remote.GoogleClientService;

@Service
@RequiredArgsConstructor
public class GoogleLoginServiceImpl implements GoogleLoginService {

	private final GoogleClientService clientService;
	private final UserRepository userRepository;

	@Override
	public String getSocialEmail(final String authCode) {
		return "".equals(authCode) ? "" : clientService.getVerifiedEmail(clientService.getIdToken(authCode));
	}

	@Override
	public boolean isRegistered(final String email) {
		return userRepository.findById(email).isPresent();
	}

}
