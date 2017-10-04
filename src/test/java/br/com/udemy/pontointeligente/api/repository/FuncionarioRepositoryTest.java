package br.com.udemy.pontointeligente.api.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.udemy.pontointeligente.api.builder.EmpresaBuilderTest;
import br.com.udemy.pontointeligente.api.builder.FuncionarioBuilderTest;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	@Autowired private FuncionarioRepository funcionarioRepository;
	@Autowired private EmpresaRepository empresaRepository;
	
	private static final String EMAIL = "email@email.com"; 
	private static final String CPF = "52118276605";
	
	
	@Before
	public void setUp() {
		
		Empresa empresa = new EmpresaBuilderTest().create().build();
		
		empresaRepository.save(empresa);
		
		Funcionario funcionario = new FuncionarioBuilderTest().create().buid();
		funcionario.setEmpresa(empresa);
		funcionario.setEmail(EMAIL);
		funcionario.setCpf(CPF);
		
		funcionarioRepository.save(funcionario);
		
	}
	
	@After
	public void tearDown() {
		
		empresaRepository.deleteAll();
		funcionarioRepository.deleteAll();
		
	}

	@Test
	public void deveBuscarPorEmail() {
		assertThat(funcionarioRepository.findByEmail(EMAIL).getEmail(), equalTo(EMAIL));
	}
	
	@Test
	public void deveBuscarPorCpf() {
		assertThat(funcionarioRepository.findByCpf(CPF).getCpf(), equalTo(CPF));
	}
	
	@Test
	public void deveBuscarPorCpfEEmail() {
		assertThat(funcionarioRepository.findByCpfOrEmail(CPF, EMAIL), is(notNullValue()));
	}
	
	@Test
	public void deveBuscarPorCpfOuEmailInvalido() {
		assertThat(funcionarioRepository.findByCpfOrEmail(CPF, "invalido@email.com"), is(notNullValue()));
	}
	
	@Test
	public void deveBuscarPorEmailOuCpfInvalido() {
		assertThat(funcionarioRepository.findByCpfOrEmail("12345678900", EMAIL), is(notNullValue()));
	}
	
}
