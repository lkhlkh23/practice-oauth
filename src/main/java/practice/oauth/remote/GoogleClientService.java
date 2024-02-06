package practice.oauth.remote;

public interface GoogleClientService {

	String getIdToken(final String authCode);

	String getVerifiedEmail(final String token);

}
