package br.com.udemy.pontointeligente.api.helper;

import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPJDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;

public class EmpresaHelper {
	
	EmpresaHelper() {
	
	}
	
	public static Empresa toEmpresa(CadastroPJDto cadastroPJDto) {
		
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());
		
		return empresa;
		
	}

}
