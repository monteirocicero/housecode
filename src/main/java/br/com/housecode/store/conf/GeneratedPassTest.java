package br.com.housecode.store.conf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratedPassTest {

	public static void main(String[] args) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encoded = encoder.encode("admin");
			System.out.println(encoded);
	}

}
