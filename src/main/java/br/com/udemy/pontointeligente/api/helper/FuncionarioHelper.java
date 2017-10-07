package br.com.udemy.pontointeligente.api.helper;

import java.math.BigDecimal;

import br.com.udemy.pontointeligente.api.enumerate.Perfil;
import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPJDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public class FuncionarioHelper {
	
	public FuncionarioHelper() {
	
	}
	
	public static Funcionario toFuncionario(CadastroPJDto cadastroPJDto) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setPerfil(Perfil.ROLE_ADMIN);
		funcionario.setSenha(PasswordHelper.geraBCrypt(cadastroPJDto.getSenha()));
		funcionario.setQuantidadeHorasAlmoco(0f);
		funcionario.setQuantidadeHorasTrabalhoDia(0f);
		funcionario.setValorHora(BigDecimal.ZERO);
		
		return funcionario;
		
	}

}
