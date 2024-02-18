package practice.oauth.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		// ba0fa90486630c235f8c394b892bbd20b58d1a76d479c663e37845af26eb2609c23c3133a0195f06
		final String password = "f2aa348231ce629967c7a0b9eafb88e9a2845a4cc4b6f4ce0d384ebde37bca7ed10f67d46f60781d";
		return new User(username, password, List.of());
	}
}
