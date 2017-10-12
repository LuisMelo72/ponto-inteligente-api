package br.com.udemy.pontointeligente.api.enumerate;

import org.apache.commons.lang3.EnumUtils;

public enum LancamentoTipo {
	
	TRABALHO_INICIO,
	TRABALHO_FIM,
	ALMOCO_INICIO,
	ALMOCO_FIM,
	PAUSA_INICIO,
	PAUSA_FIM;
	
	public static boolean isValidEnum(String value) {
		
		boolean validEnum = EnumUtils.isValidEnum(LancamentoTipo.class, value);
		
		return validEnum;
		
	}

}
