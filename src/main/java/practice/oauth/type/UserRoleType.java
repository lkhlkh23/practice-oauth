package practice.oauth.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserRoleType {

	GUEST("GUEST", "방문자"),
	NORMAL("NORMAL", "일반 사용자"),
	ADMIN("ADMIN", "관리자");

	private String role;
	private String description;

}
