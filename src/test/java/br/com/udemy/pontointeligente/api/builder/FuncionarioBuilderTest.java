package br.com.udemy.pontointeligente.api.builder;

import java.math.BigDecimal;

import br.com.udemy.pontointeligente.api.enumerate.Perfil;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.util.PasswordUtil;

public class FuncionarioBuilderTest {
	
	private Funcionario funcionario;
	
	public FuncionarioBuilderTest create() {
		
		funcionario = new Funcionario();
		funcionario.setCpf("52118276605");
		funcionario.setEmail("email@email.com");
		funcionario.setPerfil(Perfil.ROLE_USUARIO);
		funcionario.setNome("Funcionario Exemplo");
		funcionario.setSenha(PasswordUtil.geraBCrypt("123456"));
		funcionario.setEmpresa(new EmpresaBuilderTest().create().build());
		funcionario.setQuantidadeHorasAlmoco(1f);
		funcionario.setQuantidadeHorasTrabalhoDia(8.4f);
		funcionario.setValorHora(new BigDecimal("50"));
		
		return this;
		
	}
	
	public Funcionario buid() {
		
		return funcionario;
	}

}
