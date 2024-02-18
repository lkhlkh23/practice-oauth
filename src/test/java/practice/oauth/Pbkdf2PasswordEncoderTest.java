package practice.oauth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootTest(classes = Pbkdf2PasswordEncoder.class)
public class Pbkdf2PasswordEncoderTest {

	@Autowired
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
}
