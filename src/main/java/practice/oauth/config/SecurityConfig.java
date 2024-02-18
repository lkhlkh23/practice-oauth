package practice.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import lombok.RequiredArgsConstructor;
import practice.oauth.service.CustomUserDetailsService;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final CharSequence SECRET_KEY = "doblee";
	private static final int ITERATION_COUNT = 65536;
	private static final int HASH_WIDTH = 256;
	private static final int SALT_LENGTH = 16;

	private final CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.anyRequest().permitAll();
	}

	@Bean
	Pbkdf2PasswordEncoder customPasswordEncoder() {
		return new Pbkdf2PasswordEncoder(SECRET_KEY, SALT_LENGTH, ITERATION_COUNT, HASH_WIDTH);
	}

	@Bean
	public DaoAuthenticationProvider customAuthenticationProvider() {
		final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(customPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);

		final boolean result = customPasswordEncoder().matches("LEEKIHYUN", "5eeb88eafd293a3a277e21ec75aeec0f701870ce949c7695e7b5b9d819d8347fb5a928a773f651d71de521aceebc1aa1");
		System.out.println(result + "~~~~~~");
		return daoAuthenticationProvider;
	}

}
