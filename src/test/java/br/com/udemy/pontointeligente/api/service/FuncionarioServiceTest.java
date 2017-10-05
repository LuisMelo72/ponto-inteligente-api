package br.com.udemy.pontointeligente.api.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.repository.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	
	@MockBean private FuncionarioRepository funcionarioRepository;
	
	@Autowired private FuncionarioService funcionarioService;
	
	@Before
	public void setUp() throws Exception {
		
		BDDMockito.given(funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
		BDDMockito.given(funcionarioRepository.findOne(Mockito.anyLong())).willReturn(new Funcionario());
		BDDMockito.given(funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		
	}
	
	@Test
	public void devePersistirFuncionario() {
		assertThat(funcionarioService.persistir(new Funcionario()), is(notNullValue()));
	}
	
	@Test
	public void deveBuscarPorId() {
		assertThat(funcionarioService.buscaPorId(1L).isPresent(), is(true));
	}
	
	@Test
	public void deveBuscarPorCpf() {
		assertThat(funcionarioService.buscaPorCpf("77140393451").isPresent(), is(true));
	}
	
	@Test
	public void deveBuscarPorEmail() {
		assertThat(funcionarioService.buscaPorEmail("email@exemplo.com").isPresent(), is(true));
	}

	
}
