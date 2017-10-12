package br.com.udemy.pontointeligente.api.enumerate;

import java.util.Optional;

public enum LancamentoTipo {
	
	TRABALHO_INICIO,
	TRABALHO_FIM,
	ALMOCO_INICIO,
	ALMOCO_FIM,
	PAUSA_INICIO,
	PAUSA_FIM;
	
	public static Optional<LancamentoTipo> fromString(String name) {
		
		Optional<LancamentoTipo> lancamentoTipo = Optional.empty();
		
		for (LancamentoTipo tipo : LancamentoTipo.values()) {
			
			if(tipo.name().equalsIgnoreCase(name)) {
				
				lancamentoTipo = Optional.of(tipo);
				
			}
			
		}
		
		return lancamentoTipo;
		
	}

}
