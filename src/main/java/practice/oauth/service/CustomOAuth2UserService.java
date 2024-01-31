package practice.oauth.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import practice.oauth.infra.UserEntity;
import practice.oauth.infra.UserRepository;
import practice.oauth.model.User;
import practice.oauth.type.UserRoleType;
import practice.oauth.util.HttpSessionUtil;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		final String registrationId = userRequest.getClientRegistration()
												 .getRegistrationId();
		final OAuth2User oAuth2User = super.loadUser(userRequest);
		final Map<String, Object> attributes = oAuth2User.getAttributes();
		HttpSessionUtil.getInstance().setSession(httpSession, registerUser(attributes, registrationId));

		return oAuth2User;
	}

	private User registerUser(final Map<String, Object> attributes, final String registrationId) {
		final UserEntity loginUser = UserEntity.builder()
											   .email((String) attributes.get("email"))
											   .provider(registrationId)
											   .name((String) attributes.get("name"))
											   .picture((String) attributes.get("picture"))
											   .userRole(UserRoleType.NORMAL)
											   .build();
		return User.from(userRepository.save(loginUser));
	}


}
