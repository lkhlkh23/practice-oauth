package practice.oauth.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleLoginResponseV1 {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_in")
	private String expiresIn;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("id_token")
	private String idToken;

}
