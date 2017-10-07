package br.com.udemy.pontointeligente.api.helper;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PasswordHelperTest {

	private static final String SENHA = "123456";
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void deveGerarNullParaSenhaNull() {
		
		assertThat(PasswordHelper.geraBCrypt(null), nullValue());
		
	}
	
	@Test
	public void deveGerarSenhaHash() {
		
		assertThat(encoder.matches(SENHA, PasswordHelper.geraBCrypt(SENHA)), equalTo(true));
		
	}
	
}
