package br.com.udemy.pontointeligente.api.helper;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.udemy.pontointeligente.api.helper.StringHelper;

public class PasswordHelper implements Serializable {

	private static final long serialVersionUID = 2517329498437861455L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PasswordHelper.class);
	
	PasswordHelper() {
		
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
