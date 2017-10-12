package br.com.udemy.pontointeligente.api.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	
	DateHelper() {
	
	}
	
	public static Date toDate(String texto, String pattern) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		
		return dateFormat.parse(texto);
		
	}

}
