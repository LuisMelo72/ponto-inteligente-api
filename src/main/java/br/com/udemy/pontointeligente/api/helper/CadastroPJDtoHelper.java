package br.com.udemy.pontointeligente.api.helper;

import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPJDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public class CadastroPJDtoHelper {
	
	CadastroPJDtoHelper() {
	
	}
	
	public static CadastroPJDto toCadastroPJDto(Funcionario funcionario) {
		
		CadastroPJDto cadastroPJDto = convertToCadastroPJDto(funcionario);
		
		return cadastroPJDto;
		
	}

	private static CadastroPJDto convertToCadastroPJDto(Funcionario funcionario) {
		
		CadastroPJDto cadastroPJDto = new CadastroPJDto();
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		
		return cadastroPJDto;
		
	}

}
