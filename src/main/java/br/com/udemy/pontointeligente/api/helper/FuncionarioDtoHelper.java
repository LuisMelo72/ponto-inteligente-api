package br.com.udemy.pontointeligente.api.helper;

import java.util.Optional;

import br.com.udemy.pontointeligente.api.modelo.dto.FuncionarioDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public class FuncionarioDtoHelper {

	FuncionarioDtoHelper() {
	
	}

	public static FuncionarioDto toFuncionarioDto(Funcionario funcionario) {
		
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setNome(Optional.ofNullable(funcionario.getNome()));
		funcionarioDto.setEmail(Optional.ofNullable(funcionario.getEmail()));
		
		funcionario.getQuantidadeHorasAlmocoOpt().ifPresent(
			qtde -> funcionarioDto.setQuantidadeHorasAlmoco(Optional.of(Float.toString(qtde))));
		
		funcionario.getQuantidadeHorasTrabalhoDiaOpt().ifPresent(
			qtde -> funcionarioDto.setQuantidadeHorasTrabalhoDia(Optional.of(Float.toString(qtde))));
		
		funcionario.getValorHoraOpt().ifPresent(
			valor -> funcionarioDto.setValorHora(Optional.of(valor.toString())));
		
		return funcionarioDto;
		
	}
	
}
