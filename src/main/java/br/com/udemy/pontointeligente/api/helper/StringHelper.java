package br.com.udemy.pontointeligente.api.helper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static String toString(Date date, String pattern) {
		
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		
		String dataFormatada = dateFormat.format(date);
		
		return dataFormatada;
		
	}

}
