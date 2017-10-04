package br.com.udemy.pontointeligente.api.util;

import java.io.Serializable;

public class StringHelper implements Serializable {
	
	private static final long serialVersionUID = -7268796500073996551L;

	StringHelper() {
	
	}
	
	public static Boolean isNullOrEmpty(String value) {
		
		Boolean isEmpty = false;
		
		if((value == null)
				|| (value.trim().isEmpty())) {
			
			isEmpty = true;
			
		}
		
		return isEmpty;
		
	}

}
