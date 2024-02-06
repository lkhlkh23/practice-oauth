package practice.oauth.service;

public interface GoogleLoginService {

	String getSocialEmail(final String authCode);

	boolean isRegistered(final String email);

}
