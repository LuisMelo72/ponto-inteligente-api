package br.com.udemy.pontointeligente.api.service;

import java.util.Optional;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;

public interface EmpresaService {
	
	/**
	 * Retorna uma empresa dado um CNPJ
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscaPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persiste(Empresa empresa);

}
