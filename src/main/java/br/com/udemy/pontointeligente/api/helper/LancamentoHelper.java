package br.com.udemy.pontointeligente.api.helper;


import java.text.ParseException;

import br.com.udemy.pontointeligente.api.enumerate.LancamentoTipo;
import br.com.udemy.pontointeligente.api.modelo.dto.LancamentoDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

public class LancamentoHelper {
	
	LancamentoHelper() {
	
	}

	public static Lancamento toLancamento(LancamentoDto lancamentoDto) throws ParseException {
		
		Lancamento lancamento = new Lancamento();
		
		lancamento.setData(DateHelper.toDate(lancamentoDto.getData(), "yyyy-MM-dd HH:mm:ss"));
		lancamento.setDescricao(lancamentoDto.getDescricao());
		lancamento.setLocalizacao(lancamentoDto.getLocalizacao());
		lancamento.setTipo(LancamentoTipo.valueOf(lancamentoDto.getTipo()));
		
		lancamentoDto.getId().ifPresent(id -> lancamento.setId(id));
		
		return lancamento;
		
	}
	
	

}
