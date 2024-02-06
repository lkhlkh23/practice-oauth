package practice.oauth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleLoginResponseV1 {

	private String email;
	private String accessToken;
	private String refreshToken;
	private boolean isRegistered;

	public static GoogleLoginResponseV1 of(final String email) {
		return GoogleLoginResponseV1.builder()
									.email(email)
									.isRegistered(false)
									.build();
	}

}
