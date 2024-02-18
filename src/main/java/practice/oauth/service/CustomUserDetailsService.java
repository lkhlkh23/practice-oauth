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
		final String password = "ec27ce82fb5fcbdfcaca78924c2b7f0247f5bd5b15b2e47bd6bb5a43fbaf8453ce7eb0e05590c337ec765928dbf8f25b";
		return new User(username, password, List.of());
	}
}
