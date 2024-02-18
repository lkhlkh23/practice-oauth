package practice.oauth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class Pbkdf2PasswordEncoderTest {

	@Autowired
	@Qualifier("customPasswordEncoder")
	private Pbkdf2PasswordEncoder sut;

	@Test
	void test_encode() {
		// given
		final String raw = "LEEKIHYUN";

		// when
		final String result = sut.encode(raw);

		// then
		assertFalse("".equals(result));
	}

	@Test
	void name() {
		System.out.println(sut.encode("LEEKIHYUN"));
	}
}
