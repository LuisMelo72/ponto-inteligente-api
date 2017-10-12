package br.com.udemy.pontointeligente.api.helper;

import java.util.Optional;

import br.com.udemy.pontointeligente.api.modelo.dto.LancamentoDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

public class LancamentoDtoHelper {
	
	LancamentoDtoHelper() {
		
	}

	public static LancamentoDto toLancamentoDto(Lancamento lancamento) {
		
		LancamentoDto lancamentoDto = new LancamentoDto();
		lancamentoDto.setData(StringHelper.toString(lancamento.getData(), "yyyy-MM-dd HH:mm:ss"));
		lancamentoDto.setDescricao(lancamento.getDescricao());
		lancamentoDto.setFuncionarioId(lancamento.getFuncionario().getId());
		lancamentoDto.setId(Optional.of(lancamento.getId()));
		lancamentoDto.setLocalizacao(lancamento.getLocalizacao());
		lancamentoDto.setTipo(lancamento.getTipo().toString());
		
		return lancamentoDto;
		
	}

}
