package practice.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final CharSequence SECRET_KEY = "doblee";
	private static final int ITERATION_COUNT = 65536;
	private static final int HASH_WIDTH = 256;
	private static final int SALT_LENGTH = 16;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.anyRequest().permitAll();
	}

	@Bean
	Pbkdf2PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder(SECRET_KEY, SALT_LENGTH, ITERATION_COUNT, HASH_WIDTH);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
