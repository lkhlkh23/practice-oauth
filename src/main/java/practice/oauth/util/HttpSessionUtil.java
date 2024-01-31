package practice.oauth.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import practice.oauth.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpSessionUtil {

	private static final String USER_SESSION_KEY = "user_sesstion";
	private static final HttpSessionUtil INSTANCE = new HttpSessionUtil();

	public static HttpSessionUtil getInstance() {
		return INSTANCE;
	}

	public User getUserFromSession(final NativeWebRequest webRequest) {
		if (!isLoginUser(((HttpServletRequest) webRequest.getNativeRequest()).getSession())) {
			return User.GUEST_USER;
		}

		return (User) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
	}

	public void setSession(final HttpSession httpSession, final User user) {
		httpSession.setAttribute(USER_SESSION_KEY, user);
	}

	private boolean isLoginUser(final HttpSession session) {
		return !(session.getAttribute(USER_SESSION_KEY) == null);
	}


}
