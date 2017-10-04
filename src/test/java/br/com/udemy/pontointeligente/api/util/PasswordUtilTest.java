package br.com.udemy.pontointeligente.api.util;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PasswordUtilTest {

	private static final String SENHA = "123456";
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void deveGerarNullParaSenhaNull() {
		
		assertThat(PasswordUtil.geraBCrypt(null), nullValue());
		
	}
	
	@Test
	public void deveGerarSenhaHash() {
		
		assertThat(encoder.matches(SENHA, PasswordUtil.geraBCrypt(SENHA)), equalTo(true));
		
	}
	
}
