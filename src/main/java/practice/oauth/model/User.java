package practice.oauth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.oauth.infra.UserEntity;
import practice.oauth.type.UserRoleType;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

	public static final User GUEST_USER = new User("", "", "", UserRoleType.GUEST);

	private String email;
	private String name;
	private String avatar;
	private UserRoleType userRole;

	public static User from(final UserEntity entity) {
		return User.builder()
				   .email(entity.getEmail())
				   .name(entity.getName())
				   .avatar(entity.getPicture())
				   .userRole(entity.getUserRole())
				   .build();
	}

	public boolean isLoginUser() {
		return !(this.userRole == UserRoleType.GUEST);
	}

}
