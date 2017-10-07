package br.com.udemy.pontointeligente.api.service;

import java.util.Optional;

import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Persiste um funcionario na base de dados
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persiste(Funcionario funcionario);
	
	/**
	 * Busca e retorna um funcionario dado um CPF
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscaPorCpf(String cpf);
	
	/**
	 * Busca e retorna um funcionario dado um email
	 * 
	 * @param email
	 * @return Optinal<Funcionario>
	 */
	Optional<Funcionario> buscaPorEmail(String email);
	
	/**
	 * Busca e retorna um funcionario dado um ID
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscaPorId(Long id);

}
