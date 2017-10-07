package br.com.udemy.pontointeligente.api.helper;

import java.util.Optional;

import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPFDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public class CadastroPFDtoHepler {
	
	CadastroPFDtoHepler() {
	
	}

	public static CadastroPFDto toCadastroPFDto(Funcionario funcionario) {
		
		CadastroPFDto cadastroPFDto = convertToCadastroPFDto(funcionario);
		
		return cadastroPFDto;
	}

	private static CadastroPFDto convertToCadastroPFDto(Funcionario funcionario) {
		
		CadastroPFDto cadastroPFDto = new CadastroPFDto();
		cadastroPFDto.setId(funcionario.getId());
		cadastroPFDto.setCnpj(funcionario.getEmpresa().getCnpj());
		cadastroPFDto.setCpf(funcionario.getCpf());
		cadastroPFDto.setEmail(funcionario.getEmail());
		cadastroPFDto.setNome(funcionario.getNome());
		
		funcionario.getQuantidadeHorasAlmocoOpt().ifPresent(
			qtde -> cadastroPFDto.setQuantidadeHorasAlmoco(Optional.of(Float.toString(qtde))));
		
		funcionario.getQuantidadeHorasTrabalhoDiaOpt().ifPresent(
			qtde -> cadastroPFDto.setQuantidadeHorasTrabalhoDia(Optional.of(Float.toString(qtde))));
		
		funcionario.getValorHoraOpt().ifPresent(
			qtde -> cadastroPFDto.setValorHora(Optional.of(qtde.toString())));
		
		return cadastroPFDto;
		
	}

}
