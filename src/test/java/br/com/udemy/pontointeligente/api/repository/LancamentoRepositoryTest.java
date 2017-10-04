package br.com.udemy.pontointeligente.api.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.udemy.pontointeligente.api.builder.EmpresaBuilderTest;
import br.com.udemy.pontointeligente.api.builder.FuncionarioBuilderTest;
import br.com.udemy.pontointeligente.api.builder.LancamentoBuilderTest;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	
	@Autowired private LancamentoRepository lancamentoRepository;
	@Autowired private FuncionarioRepository funcionarioRepository;
	@Autowired private EmpresaRepository empresaRepository;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() {
		
		Empresa empresa = new EmpresaBuilderTest().create().build();
		
		empresaRepository.save(empresa);
		
		Funcionario funcionario = new FuncionarioBuilderTest().create().buid();
		funcionario.setEmpresa(empresa);
		
		funcionarioRepository.save(funcionario);
		
		funcionarioId = funcionario.getId();
		
		Lancamento lancamento1 = new LancamentoBuilderTest().create().build();
		lancamento1.setFuncionario(funcionario);
		
		Lancamento lancamento2 = new LancamentoBuilderTest().create().build();
		lancamento2.setFuncionario(funcionario);
		
		lancamentoRepository.save(lancamento1);
		lancamentoRepository.save(lancamento2);
		
	}
	
	@After
	public void tearDown() {
		
		empresaRepository.deleteAll();
		
		funcionarioRepository.deleteAll();
		
		lancamentoRepository.deleteAll();
		
	}
	
	@Test
	public void deveBuscarLancamentosPorFuncionarioId() {
		assertThat(lancamentoRepository.findByFuncionarioId(funcionarioId).size(), equalTo(2));
	}
	
	@Test
	public void deveBuscarLancamentosPorFuncionarioIdPaginado() {
		assertThat(lancamentoRepository.findByFuncionarioId(funcionarioId, new PageRequest(0, 10)).getTotalElements(), equalTo(2L));
	}
	

}
