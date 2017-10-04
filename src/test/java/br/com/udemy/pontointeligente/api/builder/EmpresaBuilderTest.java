package br.com.udemy.pontointeligente.api.builder;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;

public class EmpresaBuilderTest {
	
	private Empresa empresa;
	
	public EmpresaBuilderTest create() {
		
		empresa = new Empresa();
		empresa.setCnpj("70407856000161");
		empresa.setRazaoSocial("Empresa Exemplo");
		
		return this;
		
	}

	public Empresa build() {
		
		return empresa;
		
	}
	
}
