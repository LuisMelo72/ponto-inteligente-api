package br.com.udemy.pontointeligente.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

public interface LancamentoService {
	
	/**
	 * Persiste um lancamento na base de dados
	 * 
	 * @param lancamento
	 * @return
	 */
	Lancamento persiste(Lancamento lancamento);
	
	/**
	 * Remove um lancamento na base de dados
	 * 
	 * @param id
	 */
	void remove(Long id);
	
	/**
	 * Retorna um lancamento por ID
	 * 
	 * @param id
	 * @return
	 */
	Optional<Lancamento> buscaPorId(Long id);
	
	/**
	 * Retorna uma lista paginada de lancamentos de um determinado funcionario
	 * 
	 * @param funcionarioId
	 * @param pageRequest
	 * @return
	 */
	Page<Lancamento> buscaPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

}
