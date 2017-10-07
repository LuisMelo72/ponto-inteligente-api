package br.com.udemy.pontointeligente.api.helper;

import java.math.BigDecimal;

import br.com.udemy.pontointeligente.api.enumerate.Perfil;
import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPFDto;
import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPJDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

public class FuncionarioHelper {
	
	FuncionarioHelper() {
	
	}
	
	public static Funcionario toFuncionario(CadastroPJDto cadastroPJDto) {
		
		Funcionario funcionario = convertToFuncionario(cadastroPJDto);
		
		return funcionario;
		
	}
	
	public static Funcionario toFuncionario(CadastroPFDto cadastroPFDto) {
		
		Funcionario funcionario = convertToFuncionario(cadastroPFDto);
		
		return funcionario;
		
	}

	private static Funcionario convertToFuncionario(CadastroPJDto cadastroPJDto) {
		
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

	private static Funcionario convertToFuncionario(CadastroPFDto cadastroPFDto) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(cadastroPFDto.getCpf());
		funcionario.setEmail(cadastroPFDto.getEmail());
		funcionario.setNome(cadastroPFDto.getNome());
		funcionario.setPerfil(Perfil.ROLE_USUARIO);
		funcionario.setSenha(PasswordHelper.geraBCrypt(cadastroPFDto.getSenha()));
		
		cadastroPFDto.getQuantidadeHorasAlmoco().ifPresent(
			qtde -> funcionario.setQuantidadeHorasAlmoco(Float.valueOf(qtde)));
		
		cadastroPFDto.getQuantidadeHorasTrabalhoDia().ifPresent(
			qtde -> funcionario.setQuantidadeHorasTrabalhoDia(Float.valueOf(qtde)));
		
		cadastroPFDto.getValorHora().ifPresent(
			qtde -> funcionario.setValorHora(new BigDecimal(qtde.toString())));
		
		return funcionario;
		
	}

}
