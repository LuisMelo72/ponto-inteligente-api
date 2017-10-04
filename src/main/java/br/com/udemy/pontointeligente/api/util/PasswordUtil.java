package br.com.udemy.pontointeligente.api.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil implements Serializable {

	private static final long serialVersionUID = 2517329498437861455L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PasswordUtil.class);
	
	PasswordUtil() {
		
	}
	
	public static String geraBCrypt(String senha) {
		
		String senhaCriptografada = null;
		
		if(!StringHelper.isNullOrEmpty(senha)) {
			
			LOG.info("Gerando hash com BCrypt");
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			senhaCriptografada = encoder.encode(senha);
			
		}
		
		return senhaCriptografada;
		
	}

}
