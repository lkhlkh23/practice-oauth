package practice.oauth.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import practice.oauth.service.CustomOAuth2UserService;
import practice.oauth.type.UserRoleType;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService oauthService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/img/**", "/view/**").permitAll()
				.antMatchers("/api/v1/**").hasRole(UserRoleType.NORMAL.getRole())
				.anyRequest().authenticated()
			.and()
				.logout()
					.logoutSuccessUrl("/view/home")
			.and()
				.oauth2Login()
					.defaultSuccessUrl("/view/home")
					.userInfoEndpoint()
				.userService(oauthService);
	}

}
