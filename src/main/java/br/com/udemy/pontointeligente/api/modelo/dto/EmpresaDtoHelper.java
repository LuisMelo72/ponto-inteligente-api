package br.com.udemy.pontointeligente.api.modelo.dto;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;

public class EmpresaDtoHelper {
	
	EmpresaDtoHelper() {
		
	}

	public static EmpresaDto toEmpresaDto(Empresa empresa) {
		
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		
		return empresaDto;
		
	}

}
